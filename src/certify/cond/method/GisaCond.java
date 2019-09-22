package certify.cond.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

/*
 * 기사 자격증 조건문
 * 작성일자 : 19.08.27. 작성자 : 조지훈
 * 수정일자 : 19.09.03. 작성자 : 조지훈
 */

@Repository
public class GisaCond extends OverrideSource{
	
	@Autowired
	UserMethod userdao = null;
	
	@Autowired
	CertifyMethod certidao = null;
	
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
	
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	// 학력정보 리턴 간 받아올 변수
	private List<userEduVO> user_eduList = null;
	
	// 경력사항 리턴 간 받아올 변수
	private long comp_workdays = 0;
	
	// 회원이 기보유한 자격증 리스트 리턴을 위한 변수
	private List<userCertiVO> user_certiList = null;
	
	// 회원의 경력사항을 리턴받는 리스트 변수
	private List<userCareerSub> user_career_sub =null; 		// 카테고리별 근무년수(근무일수) 총합 후 저장을 위한 리스트 변수
	private HashMap<Integer, Long> careerMap = null;		// 실제 조건 비교에 사용되는 Map
	
	// 전체 자격증 종류 리스트 리턴을 위한 변수
	private List certifyList = null;
	
	String sql="";
	
	// 단일 회원의 전체 정보 가져오기
	public void getUserStatus(String id) {
		
		// 회원 개인정보
		userVO usvo = userdao.getUserInfo(id);
		
		// 회원 학력정보
		user_eduList = userdao.getUserEdu(id);
		
		// 회원 경력정보
		List<userCareerVO> returnCareer = userdao.getUserCareer(id);
		
		// 회원이 보유한 경력사항을 통합하는 과정(종목별 누적 근무일수 합산)
		if(returnCareer!=null) {
			for(int i=0; i<returnCareer.size(); i++) {
				long diff = returnCareer.get(i).com_ent_date.getTime() - returnCareer.get(i).com_gra_date.getTime();
				long diffDays = Math.abs(diff / (24 * 60 * 60 * 1000));	// 양수변환
				comp_workdays = diffDays; // 합산 근무일수
				
				userCareerSub ucs = new userCareerSub();
				ucs.setUser_car_cate(returnCareer.get(i).comp_cate);
				ucs.setUser_sub_workdays(comp_workdays);
				user_career_sub.add(ucs);
			}
			careerMap = new HashMap();
			for(int i=0; i<user_career_sub.size(); i++) {
				if(careerMap.isEmpty()) {
					careerMap.put(user_career_sub.get(i).user_car_cate, user_career_sub.get(i).user_sub_workdays);
				}else if(!careerMap.containsKey(user_career_sub.get(i).user_car_cate)) {
					careerMap.put(user_career_sub.get(i).user_car_cate, user_career_sub.get(i).user_sub_workdays);
				}else if(careerMap.containsKey(user_career_sub.get(i).user_car_cate)) {
					long workday_sum = careerMap.get(user_career_sub.get(i).user_car_cate)+user_career_sub.get(i).user_sub_workdays;
					careerMap.remove(user_career_sub.get(i).user_car_cate);
					careerMap.put(returnCareer.get(i).comp_cate, workday_sum);
				}
			}
		}
		
		// 회원 보유 자격증 정보
		List<userCertiVO> user_certiList = userdao.getUserCerti(id);
		
	}

	// 조건1. 4년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	// 매개변수 - Stirng id : 해당 시험 응시자 id, int certify_num : 응시하고자 하는 자격증 번호
	public boolean gisa_cond1(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_eduList!=null) {
			for(int i=0; i<user_eduList.size(); i++) {
				// 4년제 이상이고, 전공이 자격증의 분류와 같을때 (재학)
				if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
					long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
				    long diffDays = diff / (24 * 60 * 60 * 1000);
				    
				    // 관련학과 2년 이상 마쳤을 경우(현재기준)
				    if(diffDays>year*2) {
				    	// 입사를 한 상태라면.. (동일 및 유사직무분야에 경력이 있을 경우)	
				    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
				    		if(careerMap.get(cfvo.getCate())>=year*2) {
				    			applyPossible=true; break;
				    		}
				    	}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건2. 5년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond2(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==4 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>(year*5)/2) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*2) {
			    			applyPossible=true; break;
			    		}
			    	}
				}
			}
		}
		return applyPossible;
	}
		
	// 조건3. 6년제 대학 관련학과 1/2이상 마친 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond3(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==5 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>year*3) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
			    	}
			    }
			}	
		}
		return applyPossible;
	}	
	
	// 조건4. 관련학과 2년제 전문대학 졸업후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond4(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==1 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*2) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건5. 관련학과 3년제 전문대학 졸업후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond5(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==2 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*2) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건6. 관련학과 졸업예정자(4년제)
	public boolean gisa_cond6(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건7. 관련학과 졸업자(4년제)
	public boolean gisa_cond7(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건8. 관련학과 전공심화과정의 학사학위 취득예정자
	public boolean gisa_cond8(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==6 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}	
		}
		return applyPossible;
	}
	
	// 조건9. 관련학과 전공심화과정의 학사학위 취득자
	public boolean gisa_cond9(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu>=6 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건 10. 기능사 자격 취득 후 동일 및 유사직무분야에서 3년이상 실무에 종사한 자
	public boolean gisa_cond10(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==0) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*3) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 11. 동일 및 유사직무분야에서 4년이상 실무에 종사한 자
	public boolean gisa_cond11(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
    		if(careerMap.get(cfvo.getCate())>=year*4) {
    			applyPossible=true;
    		}
    	}
		return applyPossible;
	}
	
	// 조건 12. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "기사 수준 기술훈련과정" 이수예정자
	public boolean gisa_cond12(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==8 && user_eduList.get(i).state==2)	{
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 13. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "기사 수준 기술훈련과정" 이수자
	public boolean gisa_cond13(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==8 && user_eduList.get(i).state==0)	{
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 14. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "산업기사 수준 기술훈련과정" 이수 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond14(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==7 && user_eduList.get(i).state==0)	{
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
		    		if(careerMap.get(cfvo.getCate())>=year*2) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 15. 동일 및 유사 직무분야의 다른 종목 기사 '이상'의 자격을 취득한 자
	public boolean gisa_cond15(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type >= cfvo.getType()) {
					applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 16. 산업기사 등급 이상 자격 취득 후 동일 및 유사직무 분야에서 1년 이상 실무에 종사한 자
	public boolean gisa_cond16(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==1) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
			    		if(careerMap.get(cfvo.getCate())>=year) {
			    			applyPossible=true; break;
			    		}
			    	}
				}
			}
		}
		return applyPossible;
	}
	
	String [] messArr = {"4년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자",
		"5년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자",
		"6년제 대학 관련학과 1/2이상 마친 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자",
		"관련학과 2년제 전문대학 졸업후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자",
		"관련학과 3년제 전문대학 졸업후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자",
		"관련학과 졸업예정자(4년제)",
		"관련학과 졸업자(4년제)",
		"관련학과 전공심화과정의 학사학위 취득예정자",
		"관련학과 전공심화과정의 학사학위 취득자",
		"기능사 자격 취득 후 동일 및 유사직무분야에서 3년이상 실무에 종사한 자",
		"동일 및 유사직무분야에서 4년이상 실무에 종사한 자",
		"동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 '기사 수준 기술훈련과정' 이수예정자",
		"동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 '기사 수준 기술훈련과정' 이수자",
		"동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 '산업기사 수준 기술훈련과정' 이수 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자",
		"동일 및 유사 직무분야의 다른 종목 기사 '이상'의 자격을 취득한 자",
		"산업기사 등급 이상 자격 취득 후 동일 및 유사직무 분야에서 1년 이상 실무에 종사한 자"
	};
	
	public List<methodVO> getGisaAll(String id, int cerNum) {
		int idx = 0;
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond = false;
		String condmes = null;
		
		cond = gisa_cond1(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond2(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond3(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond4(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond5(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond6(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond7(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond8(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond9(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond10(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond11(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond12(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond13(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond14(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond15(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond16(id, cerNum);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		return checkList;
	}
	

	/*
	 * 조건에 포함되지 않은 항목들
	 * - 외국에서 동일한 종목에 해당하는 자격을 취득한 자
	 * - 학점인정등에관한법률 제7조 규정에 의하여 관련학과 106학점 이상을 인정받은 자
	 */
	
}
