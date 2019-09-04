package certify.cond.gukjun;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import certify.cond.method.OverrideSource;
import certify.vo.CertifyVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

public class Gukjun_sobangAnjun extends OverrideSource {
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
			// TODO Auto-generated method stub
			super.getUserStatus(id);
		}
			
		// (오버라이딩) 전체 자격증 중 num에 해당하는 자격증 정보 가져오기
		@Override
		public CertifyVO getCertifyStatus(int num) {
			// TODO Auto-generated method stub
			return super.getCertifyStatus(num);
		}
		
		/* 조건 1. 소방공무원법 제2조에 따른 소방공무원으로 다음 각 목의 어느 하나에 해당하는 사람
		 * 		가. 소방공무원으로 3년 이상 근무한 경력이 있는 사람
		 * 		나. 중앙소방학교 또는 지방소방학교에서 2주 이상의 소방안전교육사 관련 전문교육과정을 이수한 사람
		 */
		public boolean gukjun_sobangAnjun1(String id, int certify_num) {
			getUserStatus(id);
			CertifyVO cfvo = getCertifyStatus(certify_num);
			condition:
				if(careerMap!=null && careerMap.containsKey(cfvo.cate)) {
					if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
				}else if(user_eduList!=null) {
					for(int i=0; i<user_eduList.size(); i++) {
						if(user_eduList.get(i).getName().contains("소방학교") && user_eduList.get(i).state==0) {
							long diff = user_eduList.get(i).getEnt_date().getTime() - user_eduList.get(i).getGra_date().getTime();
							long diffDays = Math.abs(diff / (24 * 60 * 60 * 1000));	// 양수변환
							if(diffDays>=14) applyPossible=true; break condition;
						}
					}
				}
			return applyPossible;
		}
		
		// 조건 2. 초,중등교육법 제 21조에 따라 교원의 자격(정교사 1급, 2급 (각 702, 703))을 취득한 사람
		public boolean gukjun_sobangAnjun2(String id, int certify_num) {
			getUserStatus(id);
			CertifyVO cfvo = getCertifyStatus(certify_num);
			condition:
				for(int i=0; i<user_certiList.size(); i++) {
					if(user_certiList.get(i).num==702 || user_certiList.get(i).num==703) {
						applyPossible=true; break condition;
					}
				}
			return applyPossible;
		}
		
		// 조건 3. 유아교육법 제 22조에 따라 교원의 자격을 취득한 사람
		public boolean gukjun_sobangAnjun3(String id, int certify_num) {
			getUserStatus(id);
			CertifyVO cfvo = getCertifyStatus(certify_num);
			condition:
				for(int i=0; i<user_certiList.size(); i++) {
					if(user_certiList.get(i).num==703) {
						applyPossible=true; break condition;
					}
				}
			return applyPossible;
		}
		
		// 조건 4. 영유아보육법 제21조에 따라 어린이집의 원장 또는 보육교사의 자격을 취득한 사람
		//			(보육교사 자격을 취득한 사람은 보육교사 자격을 취득한 후 3년 이상의 보육업무 경력이 있는 사람만 해당)
		public boolean gukjun_sobangAnjun4(String id, int certify_num) {
			getUserStatus(id);
			CertifyVO cfvo = getCertifyStatus(certify_num);
				condition:
					for(int i=0; i<user_certiList.size(); i++) {
						if(user_certiList.get(i).num==705 || user_certiList.get(i).num==706 || user_certiList.get(i).num==707) {
							if(careerMap!=null && careerMap.containsKey(user_certiList.get(i).cate)) {
								if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
							}
						}
					}
			return applyPossible;
		}		
		
		/* 조건 5. 다음의 각 목의 어느 하나에 해당하는 기관에서 소방안전교육 관련 교과목(응급구조학과, 교육학과 또는 제 15조 제2호에 따라 소방청장이
		 * 정하여 고시하는 소방관련학과에 개설된 전공과목을 말한다.)
		 * 		가. 고등교육법 제2조제1호로부터 제6호까지의 규정 중 어느 하나에 해당하는 학교 (대학3,4,5, 산업대, 교육대, 전문대1,2 방통대15, 기술대9)
		 * 		나. 학점인정 등에 관한 법률 제3조에 따라 학습과정의 평가인정을 받은 훈련기관
		 */
		public boolean gukjun_sobangAnjun5(String id, int certify_num) {
			getUserStatus(id);
			CertifyVO cfvo = getCertifyStatus(certify_num);
			int [] var = {1,2,3,4,5,9,15};
				condition:
					for(int i=0; i<user_eduList.size(); i++) {
						for(int v=0; v<var.length; v++) {
							if(user_eduList.get(i).edu==var[v]) applyPossible=true; break condition;
						}
						if(user_eduList.get(i).edu==14 && user_eduList.get(i).name.contains("교육훈련")) {
							applyPossible=true; break condition;
						}
					}
			return applyPossible;
		}	
		
		// 조건6. 국가기술자격법 제2조제3호에 따른 국가기술자격의 직무분야 중 안전관리 분야의 기사 자격을 취득한 후 안전관리분야에 1년 이상 종사한 사람
		public boolean gukjun_sobangAnjun6(String id, int certify_num) {
			getUserStatus(id);
			CertifyVO cfvo = getCertifyStatus(certify_num);
				condition:
					for(int i=0; i<user_certiList.size(); i++) {
						if(user_certiList.get(i).num==705 || user_certiList.get(i).num==706 || user_certiList.get(i).num==707) {
							if(careerMap!=null && careerMap.containsKey(user_certiList.get(i).cate)) {
								if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true; break condition;
							}
						}
					}
			return applyPossible;
		}	
		
		
}
