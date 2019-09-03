package certify.cond.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;


public class OverrideSource {
	private Connection conn=null;
	private PreparedStatement pstmt= null;
	private ResultSet rs = null;
	private Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			Context env = (Context)((InitialContext) ctx).lookup("java:comp/env");
			DataSource ds = (DataSource)((InitialContext) env).lookup("jdbc/xe");
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
	
	// 날짜 비교를 위한 변수
	private int year = 365;	
	private Date today = new Date();
	
	// 학력정보 리턴 간 받아올 변수
	private List<userEduVO> user_eduList = null;
	
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
		userVO usvo = new userVO();
		try {
			// 회원 개인정보
			conn = getConnection();
			sql="select * from USER_INFO where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				usvo.setId(rs.getString("id"));
				usvo.setPw(rs.getString("pw"));
				usvo.setName(rs.getString("name"));
				usvo.setBirth(rs.getDate("birth"));
				usvo.setProfile(rs.getString("profile"));
				usvo.setReg(rs.getTimestamp("reg"));
				usvo.setWana(rs.getInt("wana"));
				usvo.setQual(rs.getInt("qual"));
			}
			// 회원 학력정보
			sql="select * from USER_EDU where id=?";
			userEduVO uevo = null;
			user_eduList = new ArrayList<userEduVO>();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				uevo = new userEduVO();
				uevo.id = rs.getString("id");
				uevo.edu = rs.getInt("edu");
				uevo.major = rs.getInt("major");
				uevo.state = rs.getInt("state");
				uevo.ent_date = rs.getDate("ent_date");
				uevo.gra_date = rs.getDate("gra_date");
				user_eduList.add(uevo);
			}
			
			// 회원 경력정보
			sql="select * from USER_CAREER where id=?";
			userCareerVO uscrvo = null;
			returnCareer = new ArrayList<userCareerVO>();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				uscrvo = new userCareerVO();
				uscrvo.setNum(rs.getInt("num"));
				uscrvo.setId(rs.getString("id"));
				uscrvo.setCompany(rs.getString("company"));
				uscrvo.setComp_cate(rs.getInt("comp_cate"));
				uscrvo.setCom_ent_date(rs.getDate("com_ent_date"));
				uscrvo.setCom_gra_date(rs.getDate("com_gra_date"));
				returnCareer.add(uscrvo);
			}
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
			}else{
				company = null;
				comp_cate = 0;
				com_ent_date = null;
				com_gra_date = null;
			}
			
			
			// 회원 보유 자격증 정보
			userCertiVO usctvo = new userCertiVO();
			sql="select * from USER_CERTI where id=?";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				usctvo.setNum(rs.getInt("num"));
				usctvo.setId(rs.getString("id"));
				usctvo.setCate(rs.getInt("cate"));
				usctvo.setType(rs.getInt("type"));
				usctvo.setCer_name(rs.getInt("cer_name"));
				usctvo.setCer_date(rs.getDate("cer_date"));
				user_certiList.add(usctvo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try {rs.close();}catch(SQLException s) {}}
			if(pstmt!=null) {try {pstmt.close();}catch(SQLException s) {}}
			if(conn!=null) {try {conn.close();}catch(SQLException s) {}}
		}	
	}
	
	// 전체 자격증 중 num에 해당하는 자격증 정보 리턴
	public CertifyVO getCertifyStatus(int num) {
		CertifyVO cfvo = null;
		try {
			conn = getConnection();
			sql="select * from CERTIFY_INFO where num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cfvo = new CertifyVO();
				cfvo.setNum(rs.getInt("num"));
				cfvo.setCate(rs.getInt("cate"));
				cfvo.setType(rs.getInt("type"));
				cfvo.setName(rs.getString("name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try {rs.close();}catch(SQLException s) {}}
			if(pstmt!=null) {try {pstmt.close();}catch(SQLException s) {}}
			if(conn!=null) {try {conn.close();}catch(SQLException s) {}}
		}
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