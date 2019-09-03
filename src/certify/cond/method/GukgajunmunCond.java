package certify.cond.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import certify.vo.CertifyVO;
import user.vo.userCareerSub;
import user.vo.userCareerVO;
import user.vo.userCertiVO;

public class GukgajunmunCond extends OverrideSource{
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
	
	public boolean gukjun_gameng(String id, int certify_num) {
		// 가맹거래사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gamjung(String id, int certify_num) {
		// 감정사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gamjungPeungga(String id, int certify_num) {
		// 감정평가사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gumryang(String id, int certify_num) {
		// 검량사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gumsusa(String id, int certify_num) {
		// 검수사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungMaeSa1(String id, int certify_num) {
		// 경매사(청과) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungMaeSa2(String id, int certify_num) {
		// 경매사(수산) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungMaeSa3(String id, int certify_num) {
		// 경매사(축산) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungMaeSa4(String id, int certify_num) {
		// 경매사(화훼) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungMaeSa5(String id, int certify_num) {
		// 경매사(약용) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungMaeSa6(String id, int certify_num) {
		// 경매사(양곡) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungBi1(String id, int certify_num) {
		// 경비지도사(일반) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungBi2(String id, int certify_num) {
		// 경비지도사(기게) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungYeong1(String id, int certify_num) {
		// 경영지도사(인적자원관리) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungYeong2(String id, int certify_num) {
		// 경영지도사(재무관리) : 조건 없음
		return applyPossible=true;
	}
	
	// Keep Writing .... ( 작성자 조지훈 )
	
}
