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
public class GisaCond{
	
	@Autowired
	UserMethod userdao = null;
	
	@Autowired
	CertifyMethod certidao = null;
	
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
	
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();

	// 조건1. 4년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	// 매개변수 - Stirng id : 해당 시험 응시자 id, int certify_num : 응시하고자 하는 자격증 번호
	public boolean gisa_cond1(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond2(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond3(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond4(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond5(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond6(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건7. 관련학과 졸업자(4년제)
	public boolean gisa_cond7(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건8. 관련학과 전공심화과정의 학사학위 취득예정자
	public boolean gisa_cond8(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==6 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}	
		}
		return applyPossible;
	}
	
	// 조건9. 관련학과 전공심화과정의 학사학위 취득자
	public boolean gisa_cond9(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==6 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건 10. 기능사 자격 취득 후 동일 및 유사직무분야에서 3년이상 실무에 종사한 자
	public boolean gisa_cond10(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond11(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
    		if(careerMap.get(cfvo.getCate())>=year*4) {
    			applyPossible=true;
    		}
    	}
		return applyPossible;
	}
	
	// 조건 12. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "기사 수준 기술훈련과정" 이수예정자
	public boolean gisa_cond12(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==8 && user_eduList.get(i).state==2)	{
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 13. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "기사 수준 기술훈련과정" 이수자
	public boolean gisa_cond13(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==8 && user_eduList.get(i).state==0)	{
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 14. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "산업기사 수준 기술훈련과정" 이수 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond14(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond15(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	public boolean gisa_cond16(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
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
	
	public List<methodVO> getGisaAll(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		int idx = 0;
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond = false;
		String condmes = null;
		
		cond = gisa_cond1(uvo, careerMap, user_eduList, cfvo, user_certiList);
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond2(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond3(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond4(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond5(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond6(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond7(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond8(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond9(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond10(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond11(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond12(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond13(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond14(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond15(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
		condmes = messArr[idx]; 
		++idx;
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = gisa_cond16(uvo, careerMap, user_eduList, cfvo, user_certiList);
		mvo = new methodVO();
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
