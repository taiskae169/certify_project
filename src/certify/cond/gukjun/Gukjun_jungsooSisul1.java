package certify.cond.gukjun;

import java.util.HashMap;
import java.util.List;

import certify.vo.CertifyVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

public class Gukjun_jungsooSisul1{
	
	/* 2019.09.25. 최신 note(작성자 조지훈) : 
	 * 자격증 조건 메소드를 작성하기 위한 변수가 너무 많아
	 * 일시 작성 중지
	 */
	
	
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
				
	// 조건 1. 이공계 대학 졸업 후 수도시설의 설치나 유지관리 분야에서 2년 이상 실무에 종사한 자
	public boolean gukjun_jungsooSisul1_1(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		
		
		
		return applyPossible;
	}
	
}
