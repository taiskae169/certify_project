package certify.cond.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import certify.vo.CertifyVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

/*
 * 산업기사 자격증 조건문
 * 작성일자 : 19.08.27. 작성자 : 조지훈
 */

public class SanUpCond extends OverrideSource{
	
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
		
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	// 학력정보 리턴 간 받아올 변수
	private List<userEduVO> user_eduList = null;
	
	// 회원이 기보유한 자격증 리스트 리턴을 위한 변수
	private List<userCertiVO> user_certiList = null;
	
	// 회원의 경력사항을 리턴받는 리스트 변수
	private HashMap<Integer, Long> careerMap = null;		// 실제 조건 비교에 사용되는 Map
	
	// 전체 자격증 종류 리스트 리턴을 위한 변수
	private List certifyList = null;
	
	// (오버라이딩) 단일 회원의 전체 정보 가져오기
	@Override
	public void getUserStatus(String id) {
		// TODO Auto-generated method stub
		super.getUserStatus(id);
	}
	
	// (오버라이딩) 전체 자격증 중 num에 해당하는 자격증 정보 가져오기
	@Override
	public CertifyVO getCertifyStatus(int num) {
		// TODO Auto-generated method stub
		return super.getCertifyStatus(num);
	}
	
	// 조건 1. 관련학과 2년제 전문대학 졸업예정자
	public boolean sanup_cond1(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==1 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 2. 관련학과 3년제 전문대학 졸업예정자
	public boolean sanup_cond2(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==2 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 3. 관련학과 졸업자(2년제)
	public boolean sanup_cond3(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==1 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		
		return applyPossible;
	}
	
	// 조건 4. 관련학과 졸업자(3년제)
	public boolean sanup_cond4(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==2 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 5. 관련학과 4년제 대학 전 과정의 1/2 이상 마친자
	public boolean sanup_cond5(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*2) {
			    	applyPossible=true; break;
			    }
			}
		}
		return applyPossible;
	}
	
	// 조건 6. 관련학과 5년제 대학 전 과정의 1/2 이상 마친자
	public boolean sanup_cond6(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==4 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    // 관련학과 2년 이상 마쳤을 경우(현재기준)
			    if(diffDays>=(year*5)/2) {
			    	applyPossible=true; break;
			    }
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 관련학과 6년제 대학 전 과정의 1/2 이상 마친자
	public boolean sanup_cond7(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==5 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*3) {
			    	applyPossible=true; break;
			    }
			}
		}
		return applyPossible;
	}
	
	// 조건8. 관련학과 전공심화과정의 학사학위 취득예정자
	public boolean sanup_cond8(String id, int certify_num) {
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
	public boolean sanup_cond9(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==6 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 10. 관련학과 졸업예정자(4년제)
	public boolean sanup_cond10(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}

	// 조건 11. 관련학과 졸업자(4년제)
	public boolean sanup_cond11(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 12. 기능사 자격 취득 후 동일 및 유사직무분야에서 1년이상 실무에 종사한 자
	public boolean sanup_cond12(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==0) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 13. 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean sanup_cond13(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
	   		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
	   	}
		return applyPossible;
	}
	
	// 조건 14. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "산업기사 수준 기술훈련과정" 이수예정자
	public boolean sanup_cond14(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==7 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 15. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "산업기사 수준 기술훈련과정" 이수자
	public boolean sanup_cond15(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==7 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 16. 동일 및 유사 직무분야의 다른 종목 산업기사 이상의 자격을 취득한 자
	public boolean sanup_cond16(String id, int certify_num) {
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
	
	public List<methodVO> getSanupAll(String id, int cerNum) {
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond = false;
		String condmes = null;
		boolean cond1 = sanup_cond1(id, cerNum);
		String condmes1 = "관련학과 2년제 전문대학 졸업예정자.";
		mvo.setPossible(cond1); mvo.setMess(condmes1);
		checkList.add(mvo);
		
		boolean cond2 = sanup_cond2(id, cerNum);
		String condmes2 = "관련학과 3년제 전문대학 졸업예정자.";
		mvo.setPossible(cond2); mvo.setMess(condmes2);
		checkList.add(mvo);
		
		boolean cond3 = sanup_cond3(id, cerNum);
		String condmes3 = "관련학과 졸업자(2년제)";
		mvo.setPossible(cond3); mvo.setMess(condmes3);
		checkList.add(mvo);
		
		boolean cond4 = sanup_cond4(id, cerNum);
		String condmes4 = "관련학과 졸업자(3년제)";
		mvo.setPossible(cond4); mvo.setMess(condmes4);
		checkList.add(mvo);
		
		boolean cond5 = sanup_cond5(id, cerNum);
		String condmes5 ="관련학과 4년제 대학 전 과정의 1/2 이상 마친자";
		mvo.setPossible(cond5); mvo.setMess(condmes5);
		checkList.add(mvo);
		
		boolean cond6 = sanup_cond6(id, cerNum);
		String condmes6 ="관련학과 5년제 대학 전 과정의 1/2 이상 마친자";
		mvo.setPossible(cond6); mvo.setMess(condmes6);
		checkList.add(mvo);
		
		boolean cond7 = sanup_cond7(id, cerNum);
		String condmes7 = "관련학과 6년제 대학 전 과정의 1/2 이상 마친자";
		mvo.setPossible(cond7); mvo.setMess(condmes7);
		checkList.add(mvo);
		
		boolean cond8 = sanup_cond8(id, cerNum);
		String condmes8 = "관련학과 전공심화과정의 학사학위 취득예정자";
		mvo.setPossible(cond8); mvo.setMess(condmes8);
		checkList.add(mvo);
		
		boolean cond9 = sanup_cond9(id, cerNum);
		String condmes9 = "관련학과 전공심화과정의 학사학위 취득자";
		mvo.setPossible(cond8); mvo.setMess(condmes8);
		checkList.add(mvo);
		
		cond = sanup_cond10(id, cerNum);
		condmes = "관련학과 졸업예정자(4년제)";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = sanup_cond11(id, cerNum);
		condmes = "관련학과 졸업자(4년제)";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = sanup_cond12(id, cerNum);
		condmes = "기능사 자격 취득 후 동일 및 유사직무분야에서 1년이상 실무에 종사한 자";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = sanup_cond13(id, cerNum);
		condmes = "동일 및 유사직무분야에서 2년이상 실무에 종사한 자";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = sanup_cond14(id, cerNum);
		condmes = "동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 \"산업기사 수준 기술훈련과정\" 이수예정자";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = sanup_cond15(id, cerNum);
		condmes = "동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 \"산업기사 수준 기술훈련과정\" 이수자";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		cond = sanup_cond16(id, cerNum);
		condmes = "동일 및 유사 직무분야의 다른 종목 산업기사 이상의 자격을 취득한 자";
		mvo.setPossible(cond); mvo.setMess(condmes);
		checkList.add(mvo);
		
		return checkList;
	}
	
	/*
	 * 조건에 포함되지 않은 항목들
	 * - 외국에서 동일한 종목에 해당하는 자격을 취득한 자
	 * - 학점인정등에관한법률 제7조 규정에 의하여 관련학과 106학점 이상을 인정받은 자
	 * - 학점인정등에관한법률 제7조 규정에 의하여 관련학과 81학점 이상을 인정받은 자
	 * - 학점인정등에관한법률 제7조 규정에 의하여 관련학과 41학점 이상을 인정받은 자
	 */
	
}
