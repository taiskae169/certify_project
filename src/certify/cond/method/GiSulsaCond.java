package certify.cond.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

public class GiSulsaCond{
	
	@Autowired
	UserMethod userdao = null;
	
	@Autowired
	CertifyMethod certidao = null;

	// 가능/불가능 리턴을 위한 변수
	
	
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	
	// 조건 1. 2년제 전문대학 관련학과 졸업 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond1(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==1 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 2. 3년제 전문대학 관련학과 졸업 후 동일 및 유사직무분야에서 7년 이상 실무에 종사한 자
	public boolean gisulsa_cond2(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==2 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*7) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 3. 4년제 대학 관련학과 1/2 이상 마친 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond3(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size();i++) {
			if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==1 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).getEnt_date().getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*2) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 5년제 대학 관련학과 1/2 이상 마친 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond4(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==4 && user_eduList.get(i).getState()==1 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).getEnt_date().getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*5/2) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 5. 6년제 대학 관련학과 1/2 이상 마친 후 동일 및 유사 직무분야에서 8년 이상 실무에 종사한 자
	public boolean gisulsa_cond5(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==4 && user_eduList.get(i).getState()==1 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				long diff = today.getTime() - user_eduList.get(i).getEnt_date().getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			    if(diffDays>=year*3) {
			    	if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
			    		if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
		
	// 조건 6. 고용노동부령이 정하는 교육훈련기관의 "기사수준의 기술훈련과정"이수자로서 이수후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자
	public boolean gisulsa_cond6(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==8 && user_eduList.get(i).getState()==2 && user_eduList.get(i).getMajor()==cfvo.getCate() ) {
			    if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
			    	if(careerMap.get(cfvo.getCate())>=year*6) applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 고용노동부령이 정하는 교육훈련기관의 "산업기사수준의 기술훈련과정"이수자로서 이수후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자
	public boolean gisulsa_cond7(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==7 && user_eduList.get(i).getState()==2 && user_eduList.get(i).getMajor()==cfvo.getCate() ) {
			    if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
			    	if(careerMap.get(cfvo.getCate())>=year*8) applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 8. 관련학과 전공심화과정의 학사학위 취득 후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자
	public boolean gisulsa_cond8(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==6 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				if(careerMap!=null && ( careerMap.containsKey(user_eduList.get(i).getMajor()) || careerMap.containsKey(cfvo.getCate()) )) {
			    	if(careerMap.get(cfvo.getCate())>=year*6) applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
		
	// 조건 9. 기능사 자격 취득후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자
	public boolean gisulsa_cond9(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType()==0) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*7) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}	
	
	// 조건 10. 기사 자격 취득후 동일 및 유사직무분야에서 4년이상 실무에 종사한 자
	public boolean gisulsa_cond10(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType()==2) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*4) applyPossible=true; break;
		    	}
			}
		}
		return applyPossible;
	}	
	
	// 조건 11. 대학 관련학과 졸업 후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자
	public boolean gisulsa_cond11(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
					if(careerMap.get(cfvo.getCate())>=year*6) applyPossible=true; break;
			    }
			}
		}
		return applyPossible;
	}	
	
	// 조건 12. 동일 및 유사직무분야에서 9년이상 실무에 종사한 자
	public boolean gisulsa_cond12(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
			if(careerMap.get(cfvo.getCate())>=year*9) applyPossible=true;
		}
		return applyPossible;
	}
	
	// 조건 13. 동일 및 유사직무분야의 다른 종목 기술사 자격을 취득한 자
	public boolean gisulsa_cond13(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType() == cfvo.getType()) applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 14. 산업기사 자격 취득후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자
	public boolean gisulsa_cond14(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;	
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType()==1) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
			    		if(careerMap.get(cfvo.getCate())>=year*5) applyPossible=true; break;
			    	}
				}
			}
		}
		return applyPossible;
	}
	
	String [] messArr = {"2년제 전문대학 관련학과 졸업 후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자",
			"3년제 전문대학 관련학과 졸업 후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자",
			"4년제 대학 관련학과 1/2이상 마친 후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자",
			"5년제 대학 관련학과 1/2이상 마친 후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자",
			"6년제 대학 관련학과 1/2이상 마친 후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자",
			"고용노동부령이 정하는 교육훈련기관의 '기사수준의 기술훈련과정' 이수자로서 이수후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자",
			"고용노동부령이 정하는 교육훈련기관의 '산업기사수준의 기술훈련과정' 이수자로서 이수후 동일 및 유사직무분야에서 8년이상 실무에 종사한 자",
			"관련학과 전공심화과정의 학사학위 취득 후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자",
			"기능사 자격 취득후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자",
			"기사 자격 취득후 동일 및 유사직무분야에서 4년이상 실무에 종사한 자",
			"대학 관련학과 졸업 후 동일 및 유사직무분야에서 6년이상 실무에 종사한 자",
			"동일 및 유사직무분야에서 9년이상 실무에 종사한 자",
			"동일 및 유사직무분야의 다른 종목 기술사 자격을 취득한 자",
			"산업기사 자격 취득후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자"
		};
		
		public List<methodVO> getGisulsaAll(userVO uvo, HashMap<Integer, Long> careerMap, 
				List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
			int idx = 0;
			List<methodVO> checkList = new ArrayList<methodVO>();
			methodVO mvo = new methodVO();
			boolean cond = false;
			String condmes = null;
			
			cond = gisulsa_cond1(uvo, careerMap, user_eduList, cfvo, user_certiList);
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond2(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond3(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond4(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond5(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond6(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond7(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond8(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond9(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond10(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond11(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond12(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond13(uvo, careerMap, user_eduList, cfvo, user_certiList);
			mvo = new methodVO();
			condmes = messArr[idx]; 
			++idx;
			mvo.setPossible(cond); mvo.setMess(condmes);
			checkList.add(mvo);
			
			cond = gisulsa_cond14(uvo, careerMap, user_eduList, cfvo, user_certiList);
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
	 */
	
}
