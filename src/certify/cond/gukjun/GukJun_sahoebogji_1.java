package certify.cond.gukjun;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import certify.cond.method.OverrideSource;
import certify.vo.CertifyVO;
import user.vo.userCareerSub;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

/*
 * 사회복지사 1급 조건비교 클래스
 */

public class GukJun_sahoebogji_1 extends OverrideSource{
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
		super.getUserStatus(id);
	}
	
	// (오버라이딩) 전체 자격증 중 num에 해당하는 자격증 정보 가져오기
	@Override
	public CertifyVO getCertifyStatus(int num) {
		return super.getCertifyStatus(num);
	}	
		
	
	// 조건 1. 대학원에서 사회복지를 전공하고 해당 대학원에서 사회복지 교과목(필수 6과목, 선택 2과목)을 이수하고 졸업을 한 경우(시행연도 2월 졸업자 포함)
	public boolean gukjun_sahoebogjisa1(String id, int certify_num, boolean check) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==10 && user_eduList.get(i).major==cfvo.cate && check==true) {
				// ▲ check : 웹에서 필수 6과목, 선택 2과목을 이수했는지 체크하도록 해야함
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 2. 대학교(전공무관)에서 사회복지교과목을 이수하고 졸업한 경우(시행연도 2월 졸업자 포함)
	public boolean gukjun_sahoebogjisa2(String id, int certify_num, boolean check) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==0 && check==true) {
				// ▲ check : 웹에서 사회복지교과목을 이수했는지 체크하도록 해야함
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 3. 대학교 졸업 후 전문대학 또는 대학교에 편(입)학 하여 사회복지 교과목(필수 10과목, 선택 4과목)을 이수하고 졸업한 경우(시행년도 졸업자 포함)
	public boolean gukjun_sahoebogjisa3(String id, int certify_num, boolean check) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		condition :
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==3 && user_eduList.get(i).state==0) {
				for(int j=0; j<user_eduList.size(); j++) {
					if((user_eduList.get(j).edu==1 || user_eduList.get(j).edu==2) && check == true) {
						applyPossible=true; break condition;
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 전문대학에서 사회복지교과목을 이수-졸업하고, 대학교에 (편)입학하여 졸업한 경우
	public boolean gukjun_sahoebogjisa4(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		condition :
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).edu==1 || user_eduList.get(i).edu==2) && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.cate) {
				for(int j=0; j<user_eduList.size(); j++) {
					if((user_eduList.get(j).edu==3 || user_eduList.get(j).edu==4 || user_eduList.get(j).edu==5) && user_eduList.get(j).state==0) {
						applyPossible=true; break condition;
					}
				}
			}
		}
		return applyPossible;
	}
		
	// 조건 5. 전문대학졸업 후 대학교에 편(입학)하여 사회복지교과목을 이수하고 졸업한 경우
	public boolean gukjun_sahoebogjisa5(String id, int certify_num, boolean check) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		condition :
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).edu==1 || user_eduList.get(i).edu==2) && user_eduList.get(i).state==0) {
				for(int j=0; j<user_eduList.size(); j++) {
					if((user_eduList.get(j).edu==3 || user_eduList.get(j).edu==4 || user_eduList.get(j).edu==5) && user_eduList.get(j).state==0 && check==true) {
						applyPossible=true; break condition;
					}
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 6. 학점은행제(시간제)를 통해 사회복지교과목을 이수하고 학사학위를 취득한 경우(시행연도 2월 학위취득자 포함)
	public boolean gukjun_sahoebogjisa6(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		for(int i=0; i<user_eduList.size(); i++) {
			if(user_eduList.get(i).edu==11 && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.cate) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	// 조건 7. 전문대학에서 사회복지교과목을 이수하고 졸업한 자로서 사회복지사 2급 자격증 취득일로부터 시험일까지 사회복지사업 실무경험 1년 이상인 자
	public boolean gukjun_sahoebogjisa7(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		condition : 
		for(int i=0; i<user_eduList.size(); i++) {
			if((user_eduList.get(i).edu==1 || user_eduList.get(i).edu==2 ) && user_eduList.get(i).state==0 && user_eduList.get(i).major==cfvo.cate) {
				for(int j=0; j<user_certiList.size(); j++) {
					if(user_certiList.get(j).num==000) {
						
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
	
	
	
	
	
	
	
	
}
