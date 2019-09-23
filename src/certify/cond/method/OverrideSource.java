package certify.cond.method;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

@Repository
@Controller
public class OverrideSource {

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
	
	/*
	 * 참조사항 : 
	 * <user_certi 테이블(학력)>
	 * id : 아이디, edu : 최종학교, major : 전공분야, state : 최종학력상태(졸,재 등)
	 * ent_date : 입학날짜, gra_date : 졸업날짜(졸업 시)
	 * 
	 * <user_career 테이블(경력)>
	 * company : 회사명, comp_cate : 회사 종목, com_ent_date : 입사일, com_gra_date : 퇴사일
	 * 
	 * <certify_info 테이블(자격증 (회원자격증 ㄴㄴ))>
	 * cert_num : 자격증 코드, cert_cate : 자격증 종목, cert_name : 자격증 이름
	 */
	
	
	// 전체 메소드 이용 : 단일 회원의 전체 정보 가져오기
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
	
	// 전체 자격증 중 num에 해당하는 자격증 정보 리턴
	public CertifyVO getCertifyStatus(int num) {
		CertifyVO cfvo = certidao.getSpecCertify(num);
		return cfvo;
	}
	
}

class userCareerSub {
	// 회원 경력정보 활용을 위해 임시적으로 사용하는 sub클래스입니다.
	public int user_car_cate;
	public long user_sub_workdays;
	public int getUser_car_cate() {
		return user_car_cate;
	}
	public void setUser_car_cate(int user_car_cate) {
		this.user_car_cate = user_car_cate;
	}
	public long getUser_sub_workdays() {
		return user_sub_workdays;
	}
	public void setUser_sub_workdays(long user_sub_workdays) {
		this.user_sub_workdays = user_sub_workdays;
	}	
}