package certify.cond.gukjun;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;

import certify.cond.method.OverrideSource;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

public class Gukjun_jungsooSisul1 extends OverrideSource{
	
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
	private ArrayList<userCareerVO> returnCareer = null;;
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
	
	// 조건 1. 이공계 대학 졸업 후 수도시설의 설치나 유지관리 분야에서 2년 이상 실무에 종사한 자
	public boolean gukjun_jungsooSisul1_1(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		
		// CSV를 참조하여 DB에 저장된 major(Integer)와 
		
		
		return applyPossible;
	}
	
}
