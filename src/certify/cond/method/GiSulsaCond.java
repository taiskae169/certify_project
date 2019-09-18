package certify.cond.method;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import certify.vo.CertifyVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

public class GiSulsaCond extends OverrideSource{
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
	
	// 조건 1. 2년제 전문대학 관련학과 졸업 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond1(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==1 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 2. 3년제 전문대학 관련학과 졸업 후 동일 및 유사직무분야에서 7년 이상 실무에 종사한 자
	public boolean gisulsa_cond2(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==2 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*7) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 3. 4년제 대학 관련학과 1/2 이상 마친 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond3(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size();i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*2) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 5년제 대학 관련학과 1/2 이상 마친 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond4(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==4 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*5/2) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 5. 6년제 대학 관련학과 1/2 이상 마친 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond5(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==4 && user_eduList.get(i).state==1 && user_eduList.get(i).major==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).ent_date.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*3) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
		
	// 조건 6. 고용노동부령이 정하는 교육훈련기관의 "기사수준의 기술훈련과정"이수자로서 이수후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자
	public boolean gisulsa_cond6(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==8 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate() ) {
			    if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    	if(careerMap.get(cfvo.getCate())>=year*6) applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 고용노동부령이 정하는 교육훈련기관의 "산업기사수준의 기술훈련과정"이수자로서 이수후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자
	public boolean gisulsa_cond7(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==7 && user_eduList.get(i).state==2 && user_eduList.get(i).major==cfvo.getCate() ) {
			    if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    	if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 8. 관련학과 전공심화과정의 학사학위 취득 후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자
	public boolean gisulsa_cond8(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==6 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).major) || careerMap.containsKey(cfvo.getCate()) )) {
			    	if(careerMap.get(cfvo.getCate())>=year*6) applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
		
	// 조건 9. 기능사 자격 취득후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자
	public boolean gisulsa_cond9(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==0) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*7) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}	
	
	// 조건 10. 기사 자격 취득후 동일 및 유사직무분야에서 4년이상 실무에 종사한 자
	public boolean gisulsa_cond10(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==2) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*4) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}	
	
	// 조건 11. 대학 관련학과 졸업 후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자
	public boolean gisulsa_cond11(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.getCate()) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
					if(careerMap.get(cfvo.getCate())>=year*6) applyPossible=true; break;
			    }
			}
		}
		return applyPossible;
	}	
	
	// 조건 12. 동일 및 유사직무분야에서 9년이상 실무에 종사한 자
	public boolean gisulsa_cond12(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
			if(careerMap.get(cfvo.getCate())>=year*9) applyPossible=true;
		}
		return applyPossible;
	}
	
	// 조건 13. 동일 및 유사직무분야의 다른 종목 기술사 자격을 취득한 자
	public boolean gisulsa_cond13(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type == cfvo.getType()) applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 14. 산업기사 자격 취득후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자
	public boolean gisulsa_cond14(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==1) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
			    		if(careerMap.get(cfvo.getCate())>=year*5) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
	

	/*
	 * 조건에 포함되지 않은 항목들
	 * - 외국에서 동일한 종목에 해당하는 자격을 취득한 자
	 */
	
}
