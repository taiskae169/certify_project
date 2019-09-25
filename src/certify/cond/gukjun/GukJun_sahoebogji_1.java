package certify.cond.gukjun;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

/*
 * 사회복지사 1급 조건비교 클래스
 */

public class GukJun_sahoebogji_1{
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
		
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	
	// 조건 1. 대학원에서 사회복지를 전공하고 해당 대학원에서 사회복지 교과목(필수 6과목, 선택 2과목)을 이수하고 졸업을 한 경우(시행연도 2월 졸업자 포함)
	public boolean gukjun_sahoebogjisa1(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList, boolean check) {
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).getEdu()==10 || user_eduList.get(i).getEdu()==11) && user_eduList.get(i).getMajor()==cfvo.getCate() && check==true) {
				// ▲ check : 웹에서 필수 6과목, 선택 2과목을 이수했는지 체크하도록 해야함
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 2. 대학교(전공무관)에서 사회복지교과목을 이수하고 졸업한 경우(시행연도 2월 졸업자 포함)
	public boolean gukjun_sahoebogjisa2(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList, boolean check) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==0 && check==true) {
				// ▲ check : 웹에서 사회복지교과목을 이수했는지 체크하도록 해야함
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 3. 대학교 졸업 후 전문대학 또는 대학교에 편(입)학 하여 사회복지 교과목(필수 10과목, 선택 4과목)을 이수하고 졸업한 경우(시행년도 졸업자 포함)
	public boolean gukjun_sahoebogjisa3(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList, boolean check) {
		condition :
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==0) {
				for(int j=0; j<user_eduList.size(); j++) {
					if((user_eduList.get(j).getEdu()==1 || user_eduList.get(j).getEdu()==2) && check == true) {
						applyPossible=true; break condition;
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 전문대학에서 사회복지교과목을 이수-졸업하고, 대학교에 (편)입학하여 졸업한 경우
	public boolean gukjun_sahoebogjisa4(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition :
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).getEdu()==1 || user_eduList.get(i).getEdu()==2) && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				for(int j=0; j<user_eduList.size(); j++) {
					if((user_eduList.get(j).getEdu()==3 || user_eduList.get(j).getEdu()==4 || user_eduList.get(j).getEdu()==5) && user_eduList.get(j).getState()==0) {
						applyPossible=true; break condition;
					}
				}
			}
		}
		return applyPossible;
	}
		
	// 조건 5. 전문대학졸업 후 대학교에 편(입학)하여 사회복지교과목을 이수하고 졸업한 경우
	public boolean gukjun_sahoebogjisa5(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList, boolean check) {
		condition :
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).getEdu()==1 || user_eduList.get(i).getEdu()==2) && user_eduList.get(i).getState()==0) {
				for(int j=0; j<user_eduList.size(); j++) {
					if((user_eduList.get(j).getEdu()==3 || user_eduList.get(j).getEdu()==4 || user_eduList.get(j).getEdu()==5) && user_eduList.get(j).getState()==0 && check==true) {
						applyPossible=true; break condition;
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 6. 학점은행제(시간제)를 통해 사회복지교과목을 이수하고 학사학위를 취득한 경우(시행연도 2월 학위취득자 포함)
	public boolean gukjun_sahoebogjisa6(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).getEdu()==12 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 전문대학에서 사회복지교과목을 이수하고 졸업한 자로서 사회복지사 2급 자격증 취득일로부터 시험일까지 사회복지사업 실무경험 1년 이상인 자
	public boolean gukjun_sahoebogjisa7(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition : 
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).getEdu()==1 || user_eduList.get(i).getEdu()==2 ) && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
				for(int j=0; j<user_certiList.size(); j++) {
					if(user_certiList.get(j).getNum()==000) {
						/*
						 * 자격시험 일정과 자격증 취득일을 비교하는 문구 필요
						 */
						if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
				    		if(careerMap.get(cfvo.getCate())>=year) {
				    			applyPossible=true; break;
				    		}
				    	}
					}
				}
				applyPossible=true; break condition;
			}
		}
		return applyPossible;
	}
	
	// 조건 8. 전문대학 졸업 후 학점은행제를 통해 사회복지 교과목을 이수한 자로서 사회복지사 2급 자격증 취득일로부터 시험일까지 사회복지 실무경험이 1년 이상인 자
		
	// 조건 9. 사회복지사 양성과정 수료자(시행연도 2월 28일까지)
	
	// 조건10. 외국 대학(원) 졸업자(사회복지학 또는 사회사업학 학사학위 이상 소지자)
	
	// 조건 11. 외국대학 졸업 후 학점은행제를 통해 사회복지 교과목을 이수한 자(시행연도 2월 28일까지)
	
	// 조건 12. 학점은행제를 통해 사회복지 교과목을 이수하고, 전문학사 학위를 취득한 자로서 실무경력 1년 이상인 자
	
	
	
	
	
}
