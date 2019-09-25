package certify.cond.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

public class GinunJangCond{
	
	@Autowired
	UserMethod userdao = null;
	
	@Autowired
	CertifyMethod certidao = null;	
	
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	
	// 조건 1. 기능사 자격 취득 후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자
	public boolean ginunjang_cond1(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType()==0) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*7) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}	
	
	// 조건 2. 동일 및 유사직무분야에서 9년이상 실무에 종사한 자
	public boolean ginunjang_cond2(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
			if(careerMap.get(cfvo.getCate())>=year*9) applyPossible=true;
		}
		return applyPossible;
	}
	
	// 조건 3. 동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수예정자
	public boolean ginunjang_cond3(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		if(user_certiList!=null) {
			condition : 
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType() == 0) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).getEdu()==9 && user_eduList.get(j).getState()==2)	{
							applyPossible=true; break condition;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수자
	public boolean ginunjang_cond4(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType() == 0) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).getEdu()==9 && user_eduList.get(j).getState()==0)	{
							applyPossible=true; break;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 5. 동일 및 유사직무분야의 다른 종목 기능장 자격을 취득한 자
	public boolean ginunjang_cond5(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType() == cfvo.getType()) {
					applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 6. 동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수예정자
	public boolean ginunjang_cond6(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType() == 1) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).getEdu()==9 && user_eduList.get(j).getState()==2)	{
							applyPossible=true; break;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수자
	public boolean ginunjang_cond7(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType() == 1) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).getEdu()==9 && user_eduList.get(j).getState()==0)	{
							applyPossible=true; break;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 8. 산업기사 등급 이상 자격 취득 후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자
	public boolean ginunjang_cond8(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		boolean applyPossible = false;
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getCate()==cfvo.getCate() && user_certiList.get(i).getType()>=1) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*5) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	public List<methodVO> getGinunjangAll(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond1 = ginunjang_cond1(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes1 = "기능사 자격 취득 후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자.";
		mvo.setPossible(cond1); mvo.setMess(condmes1);
		checkList.add(mvo);
		
		boolean cond2 = ginunjang_cond2(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes2 = "동일 및 유사직무분야에서 9년이상 실무에 종사한 자.";
		mvo = new methodVO();
		mvo.setPossible(cond2); mvo.setMess(condmes2);
		checkList.add(mvo);
		
		boolean cond3 = ginunjang_cond3(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes3 = "동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수예정자.";
		mvo.setPossible(cond3); mvo.setMess(condmes3);
		mvo = new methodVO();
		checkList.add(mvo);
		
		boolean cond4 = ginunjang_cond4(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes4 = "동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수자.";
		mvo.setPossible(cond4); mvo.setMess(condmes4);
		mvo = new methodVO();
		checkList.add(mvo);
		
		boolean cond5 = ginunjang_cond5(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes5 ="동일 및 유사직무분야의 다른 종목 기능장 자격을 취득한 자.";
		mvo.setPossible(cond5); mvo.setMess(condmes5);
		mvo = new methodVO();
		checkList.add(mvo);
		
		boolean cond6 = ginunjang_cond6(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes6 ="동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수예정자.";
		mvo.setPossible(cond6); mvo.setMess(condmes6);
		mvo = new methodVO();
		checkList.add(mvo);
		
		boolean cond7 = ginunjang_cond7(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes7 = "동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수자";
		mvo.setPossible(cond7); mvo.setMess(condmes7);
		mvo = new methodVO();
		checkList.add(mvo);
		
		boolean cond8 = ginunjang_cond8(uvo, careerMap, user_eduList, cfvo, user_certiList);
		String condmes8 = "산업기사 등급 이상 자격 취득 후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자";
		mvo.setPossible(cond8); mvo.setMess(condmes8);
		mvo = new methodVO();
		checkList.add(mvo);
		
		
		return checkList;
	}
	
	public void method1() {
		System.out.println("됨");
	}
	

	/*
	 * 조건에 포함되지 않은 항목들
	 * - 외국에서 동일한 종목에 해당하는 자격을 취득한 자
	 */
	
}