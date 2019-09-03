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
	private int edu = 0;
	private int major = 0;
	private int state = 0;
	private Date ent_date = null;
	private Date gra_date = null;
	
	// 경력사항 리턴 간 받아올 변수
	private String company = null;
	private int comp_cate = 0;
	private Date com_ent_date = null;
	private Date com_gra_date = null;
	private long comp_workdays = 0;
		
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
	
	// 조건 1. 대학원에서 사회복지를 전공하고 해당 대학원에서 사회복지 교과목(필수 6과목, 선택 2과목)을 이수하고 졸업을 한 경우(시행연도 2월 졸업자 포함)
	public boolean gukjun_sahoebogjisa1(String id, int certify_num, boolean check) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu==10 && major==cfvo.cate && check==true) applyPossible=true;
		return applyPossible;
	}
	
	// 조건 2. 대학교(전공무관)에서 사회복지교과목을 이수하고 졸업한 경우(시행연도 2월 졸업자 포함)
	public boolean gukjun_sahoebogjisa2(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu==3 && state==0 && major==cfvo.cate) applyPossible=true;
		return applyPossible;
	}
	
	// 조건 3. 대학교 졸업 후 전문대학 또는 대학교에 편(입)학 하여 사회복지 교과목(필수 10과목, 선택 4과목)을 이수하고 졸업한 경우(시행년도 졸업자 포함)
	public boolean gukjun_sahoebogjisa3(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu==3 && state==0 && major==cfvo.cate) applyPossible=true;
		return applyPossible;
	}
		
}
