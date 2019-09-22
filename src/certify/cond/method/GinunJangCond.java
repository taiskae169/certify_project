package certify.cond.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

public class GinunJangCond extends OverrideSource{

	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	// 학력정보 리턴 간 받아올 변수
	private List<userEduVO> user_eduList = null;
		
	// 회원이 기보유한 자격증 리스트 리턴을 위한 변수
	private List<userCertiVO> user_certiList = null;
	
	// 회원의 경력사항을 리턴받는 리스트 변수
	private ArrayList<userCareerVO> returnCareer = null;	// DB에 저장된 리스트를 리턴할 변수
	private List<userCareerSub> user_career_sub =null; 		// 카테고리별 근무년수(근무일수) 총합 후 저장을 위한 리스트 변수
	private HashMap<Integer, Long> careerMap = null;		// 실제 조건 비교에 사용되는 Map
	
	// 전체 자격증 종류 리스트 리턴을 위한 변수
	private List certifyList = null;
	
	// (오버라이딩) 단일 회원의 전체 정보 가져오기
	@Override
	public void getUserStatus(String id) {
		super.getUserStatus(id);
	}
		
	// (오버라이딩) 전체 자격증 중 num에 해당하는 자격증 정보 가져오기
	@Override
	public CertifyVO getCertifyStatus(int num) {
		return super.getCertifyStatus(num);
	}
	
	// 조건 1. 기능사 자격 취득 후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자
	public boolean ginunjang_cond1(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type==0) {
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
	public boolean ginunjang_cond2(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
			if(careerMap.get(cfvo.getCate())>=year*9) applyPossible=true;
		}
		return applyPossible;
	}
	
	// 조건 3. 동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수예정자
	public boolean ginunjang_cond3(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			condition : 
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type == 0) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).edu==9 && user_eduList.get(j).state==2)	{
							applyPossible=true; break condition;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수자
	public boolean ginunjang_cond4(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type == 0) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).edu==9 && user_eduList.get(j).state==0)	{
							applyPossible=true; break;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 5. 동일 및 유사직무분야의 다른 종목 기능장 자격을 취득한 자
	public boolean ginunjang_cond5(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type == cfvo.getType()) {
					applyPossible=true; break;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 6. 동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수예정자
	public boolean ginunjang_cond6(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type == 1) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).edu==9 && user_eduList.get(j).state==2)	{
							applyPossible=true; break;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수자
	public boolean ginunjang_cond7(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type == 1) {
					for(int j=0; j<user_eduList.size();j++) {
						if(user_eduList.get(j).edu==9 && user_eduList.get(j).state==0)	{
							applyPossible=true; break;
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 8. 산업기사 등급 이상 자격 취득 후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자
	public boolean ginunjang_cond8(String id, int certify_num) {
		boolean applyPossible = false;
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.getCate() && user_certiList.get(i).type>=1) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*5) {
		    			applyPossible=true; break;
		    		}
		    	}
			}
		}
		return applyPossible;
	}
	
	public List<methodVO> getGinunjangAll(String id, int cerNum) {
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond1 = ginunjang_cond1(id, cerNum);
		String condmes1 = "기능사 자격 취득 후 동일 및 유사직무분야에서 7년이상 실무에 종사한 자.";
		mvo.setPossible(cond1); mvo.setMess(condmes1);
		checkList.add(mvo);
		
		boolean cond2 = ginunjang_cond2(id, cerNum);
		String condmes2 = "동일 및 유사직무분야에서 9년이상 실무에 종사한 자.";
		mvo.setPossible(cond2); mvo.setMess(condmes2);
		checkList.add(mvo);
		
		boolean cond3 = ginunjang_cond3(id, cerNum);
		String condmes3 = "동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수예정자.";
		mvo.setPossible(cond3); mvo.setMess(condmes3);
		checkList.add(mvo);
		
		boolean cond4 = ginunjang_cond4(id, cerNum);
		String condmes4 = "동일 및 유사직무분야의 기능사 자격 취득 후 기능대학의 기능장 과정 이수자.";
		mvo.setPossible(cond4); mvo.setMess(condmes4);
		checkList.add(mvo);
		
		boolean cond5 = ginunjang_cond5(id, cerNum);
		String condmes5 ="동일 및 유사직무분야의 다른 종목 기능장 자격을 취득한 자.";
		mvo.setPossible(cond5); mvo.setMess(condmes5);
		checkList.add(mvo);
		
		boolean cond6 = ginunjang_cond6(id, cerNum);
		String condmes6 ="동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수예정자.";
		mvo.setPossible(cond6); mvo.setMess(condmes6);
		checkList.add(mvo);
		
		boolean cond7 = ginunjang_cond7(id, cerNum);
		String condmes7 = "동일 및 유사직무분야의 산업기사 자격 취득 후 기능대학의 기능장 과정 이수자";
		mvo.setPossible(cond7); mvo.setMess(condmes7);
		checkList.add(mvo);
		
		boolean cond8 = ginunjang_cond8(id, cerNum);
		String condmes8 = "산업기사 등급 이상 자격 취득 후 동일 및 유사직무분야에서 5년이상 실무에 종사한 자";
		mvo.setPossible(cond8); mvo.setMess(condmes8);
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