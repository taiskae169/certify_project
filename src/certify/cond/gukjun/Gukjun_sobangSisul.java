package certify.cond.gukjun;

import java.util.HashMap;
import java.util.List;

import certify.vo.CertifyVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

public class Gukjun_sobangSisul{
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
			
	// 날짜 비교를 위한 변수
	private int year = 365;	
	
	// 조건 1. 소방기술사(408), 위험물기능장(468), 기계설비기술사(380, 403), 전기설비기술사(382) 또는 공조냉동기계기술사(385) 보유자
	public boolean gukjun_sobangsisul1(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		int [] cer_var = {408, 468, 380, 403, 382, 385};
		condition:
		for(int i=0; i<user_certiList.size(); i++) {
			for(int j=0; j<cer_var.length; j++) {
				if(user_certiList.get(i).getNum()==cer_var[j]) {
					applyPossible=true; break condition;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 2. 소방설비기사(307, 308) 자격을 취득한 후 2년 이상 소방청장이 정하여 고시하는 소방에 관한 실무경력(이하 "소방실무경력")이 있는 사람
	public boolean gukjun_sobangsisul2(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
		for(int i=0; i<user_certiList.size(); i++) {
			if((user_certiList.get(i).getNum()==307 || user_certiList.get(i).getNum()==308)) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
					if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true; break condition;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 3. 소방설비산업기사(202,203) 자격을 취득한 후 2년 이상 소방실무경력이 있는 사람
	public boolean gukjun_sobangsisul3(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
		for(int i=0; i<user_certiList.size(); i++) {
			if((user_certiList.get(i).getNum()==202 || user_certiList.get(i).getNum()==203)) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
					if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 4. 소방공무원으로 5년 이상 근무한 경력이 있는 사람
	public boolean gukjun_sobangsisul4(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
			if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true;
		}
		return applyPossible;
	}

	// 조건 5. 위험물산업기사(217) 또는 위험물기능사(92) 자격을 취득한 후 3년 이상 소방실무경력이 있는 사람
	public boolean gukjun_sobangsisul5(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
		for(int i=0; i<user_certiList.size(); i++) {
			if((user_certiList.get(i).getNum()==217 || user_certiList.get(i).getNum()==92)) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
					if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
				}
			}
		}
		return applyPossible;
	}
	
	// 조건 6. 산업안전기사(300) 자격을 취득하 후 3년 이상 소방실무경력이 있는 사람
	public boolean gukjun_sobangsisul6(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getNum()==300) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
					if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
				}
			}
		}
		return applyPossible;
	}
	
	/*	조건 7.
	 * 국가과학기술경쟁력강화를위한 이공계지원 특별법 제2조제1호에 따른 이공계(이하 이공계)분야를
	 * 전공한 사람으로서 다음 각 목의 어느 하나에 해당하는 사람
	 * 		가. 이공계 분야의 박사학위를 취득한 사람
	 * 		나. 이공계 분야의 석사학위를 취득한 후 2년 이상 소방실무경력이 있는 사람
	 * 		다. 이공계 분야의 학사학위를 취득한 후 3년 이상 소방실무경력이 있는 사람
	 */
	public boolean gukjun_sobangsisul7(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
			for(int i=0; i<user_eduList.size(); i++) {
				if(user_eduList.get(i).getEdu()==11 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
					// 박사학위취득일때
					applyPossible=true; break condition;
				}else if(user_eduList.get(i).getEdu()==10 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
						if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true; break condition;
					}
				}else if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
						if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
					}
				}
			}
		return applyPossible;
	}
	
	/* 조건 8. 소방안전공학(소방방재공학, 안전공학을 포함한다) 분야를 전공한 후 다음 각 목의 어느 하나에 해당하는 사람
	 * 		가. 해당 분야의 석사학위 이상을 취득한 사람
	 * 		나. 2년 이상 소방실무경력이 있는 사람
	 */
	public boolean gukjun_sobangsisul8(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
			for(int i=0; i<user_eduList.size(); i++) {
				if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
					for(int j=0; j<user_eduList.size(); j++) {
						if(user_eduList.get(j).getEdu()==10 && user_eduList.get(j).getState()==0 && user_eduList.get(j).getMajor()==cfvo.getCate()) {
							applyPossible=true; break condition;
						}else if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
							if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true; break condition;
						}
					}
				}
			}
		return applyPossible;
	}
	
	// 조건 9. 소방안전 관련 학과의 학사학위를 취득한 후 3년 이상 소방실무경력이 있는 사람
	public boolean gukjun_sobangsisul9(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition:
			for(int i=0; i<user_eduList.size(); i++) {
				if(user_eduList.get(i).getEdu()==3 && user_eduList.get(i).getState()==0 && user_eduList.get(i).getMajor()==cfvo.getCate()) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
						if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
					}
				}
			}
		return applyPossible;
	}
	
	/* 조건 10. 다음 각 목의 어느 하나에 해당하는 사람
	 * 		가. 특급 소방안전관리대상물의 소방안전관리자로 2년 이상 근무한 실무경력이 있는 사람
	 * 		나. 1급 소방안전관리대상물의 소방안전관리자로 3년 이상 근무한 실무경력이 있는 사람
	 * 		다. 3급 소방안전관리대상물의 소방안전관리자로 7년 이상 근무한 실무경력이 있는 사람
	 * 		마. 10년 이상 소방실무경력이 있는 사람
	 */
	// 조건 10은 판별 불가
	
	// 조건 11. 건축사 자격 취득
	public boolean gukjun_sobangsisul11(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getNum()==701) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	
}
