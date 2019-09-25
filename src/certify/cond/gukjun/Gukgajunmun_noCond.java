package certify.cond.gukjun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import certify.cond.method.methodVO;
import certify.vo.CertifyVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;

public class Gukgajunmun_noCond{
	// 가능/불가능 리턴을 위한 변수
	private boolean applyPossible = false;	
		
	// 날짜 비교를 위한 변수
	private int year = 365;	
	
	
	// 조건없는 자격증 리스트 ▼
	
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
	
	public boolean gukjun_gyeungYeong3(String id, int certify_num) {
		// 경영지도사(생산관리) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungYeong4(String id, int certify_num) {
		// 경영지도사(마케팅관리) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gyeungYeong5(String id, int certify_num) {
		// 경영지도사(1차 공통) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gongin_nomu(String id, int certify_num) {
		// 공인노무사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gongin_junggae(String id, int certify_num) {
		// 공인중개사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong1(String id, int certify_num) {
		// 관광통역안내사(불어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong2(String id, int certify_num) {
		// 관광통역안내사(독어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong3(String id, int certify_num) {
		// 관광통역안내사(스페인어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong4(String id, int certify_num) {
		// 관광통역안내사(러시아어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong5(String id, int certify_num) {
		// 관광통역안내사(중국어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong6(String id, int certify_num) {
		// 관광통역안내사(이탈리아어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong7(String id, int certify_num) {
		// 관광통역안내사(태국어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong8(String id, int certify_num) {
		// 관광통역안내사(베트남어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong9(String id, int certify_num) {
		// 관광통역안내사(말레이/인도네시아어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong10(String id, int certify_num) {
		// 관광통역안내사(아랍어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanTong11(String id, int certify_num) {
		// 관광통역안내사(영어) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gwanSaeSa(String id, int certify_num) {
		// 관세사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_guknaeTravel(String id, int certify_num) {
		// 국내여행안내사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido1(String id, int certify_num) {
		// 기술지도사(1차 공통) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido2(String id, int certify_num) {
		// 기술지도사(기계) : 조건 없음
		return applyPossible=true;
	}

	public boolean gukjun_gisulJido3(String id, int certify_num) {
		// 기술지도사(금속) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido4(String id, int certify_num) {
		// 기술지도사(전기전자) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido5(String id, int certify_num) {
		// 기술지도사(섬유) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido6(String id, int certify_num) {
		// 기술지도사(화공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido7(String id, int certify_num) {
		// 기술지도사(생산관리) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido8(String id, int certify_num) {
		// 기술지도사(정보처리) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido9(String id, int certify_num) {
		// 기술지도사(환경) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_gisulJido10(String id, int certify_num) {
		// 기술지도사(생명공학) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_nongSan(String id, int certify_num) {
		// 농산물품질관리사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri1(String id, int certify_num) {
		// 문화재수리기능자(대목수) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri2(String id, int certify_num) {
		// 문화재수리기능자(소목수) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri3(String id, int certify_num) {
		// 문화재수리기능자(가공석공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri4(String id, int certify_num) {
		// 문화재수리기능자(쌓기석공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri5(String id, int certify_num) {
		// 문화재수리기능자(화공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri6(String id, int certify_num) {
		// 문화재수리기능자(드잡이공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri7(String id, int certify_num) {
		// 문화재수리기능자(번와와공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri8(String id, int certify_num) {
		// 문화재수리기능자(제작와공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri9(String id, int certify_num) {
		// 문화재수리기능자(한식미장공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri10(String id, int certify_num) {
		// 문화재수리기능자(철물공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri11(String id, int certify_num) {
		// 문화재수리기능자(목조각공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri12(String id, int certify_num) {
		// 문화재수리기능자(석조각공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri13(String id, int certify_num) {
		// 문화재수리기능자(칠공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri14(String id, int certify_num) {
		// 문화재수리기능자(도금공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri15(String id, int certify_num) {
		// 문화재수리기능자(표구공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri16(String id, int certify_num) {
		// 문화재수리기능자(조경공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri17(String id, int certify_num) {
		// 문화재수리기능자(세척공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri18(String id, int certify_num) {
		// 문화재수리기능자(훈증공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri19(String id, int certify_num) {
		// 문화재수리기능자(보존처리공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri20(String id, int certify_num) {
		// 문화재수리기능자(식물보호공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri21(String id, int certify_num) {
		// 문화재수리기능자(실측설계사보) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri22(String id, int certify_num) {
		// 문화재수리기능자(박제 및 표본제작공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri23(String id, int certify_num) {
		// 문화재수리기능자(온돌공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_munhwajaeSuri24(String id, int certify_num) {
		// 문화재수리기능자(모사공) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_mulu_gwanri(String id, int certify_num) {
		// 물류관리사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_bakmul_misul(String id, int certify_num) {
		// 박물관 및 미술관 준학예사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sanupBogun1(String id, int certify_num) {
		// 산업보건지도사(직업환경의학) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sanupBogun2(String id, int certify_num) {
		// 산업보건지도사(산업위생공학) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sanupAnjun1(String id, int certify_num) {
		// 산업보건지도사(기계안전) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sanupAnjun2(String id, int certify_num) {
		// 산업보건지도사(전기안전) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sanupAnjun3(String id, int certify_num) {
		// 산업보건지도사(화공안전) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sanupAnjun4(String id, int certify_num) {
		// 산업보건지도사(건설안전) : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_semusa(String id, int certify_num) {
		// 세무사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_sonhae(String id, int certify_num) {
		// 손해평가사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_suSanMul(String id, int certify_num) {
		// 수산물품질관리사 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_jutekGwanri(String id, int certify_num) {
		// 주택관리사보 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_hangukEuEdu(String id, int certify_num) {
		// 한국어교육능력검정시험 : 조건 없음
		return applyPossible=true;
	}
	
	public boolean gukjun_hotelservice(String id, int certify_num) {
		// 호텔서비스사 : 조건 없음
		return applyPossible=true;
	}
	
	// 조건 없는 자격증 리스트 끝
	
	
	// 조건 있는 자격증리스트 ▼
	// 문화재수리기술자 (보수, 단청, 조경, 보존과학, 식물보호)
	// cerNum : 662	663	665	666	667

	public boolean gukjun_munhwajae_gisulja(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		condition :
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate())){
			for(int i=0; i<user_eduList.size(); i++) {
				if(careerMap.get(cfvo.getCate())>=year && user_eduList.get(i).getEdu()>=0) {
					for(int j=0; j<user_certiList.size(); j++) {
						for(int num=639; num<=662; num++) {
							if(user_certiList.get(j).getNum()==num){
								applyPossible=true; break condition;
							}
						}
					}
				}
			}
		}
		return applyPossible;
	}
	
	
	// 문화재수리기술자(실측설계) 664
	public boolean gukjun_munhwa_gisulja_silchk(
			userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		/* 문화재 수리를 위한 실측 및 설계도서의 작성 업무를 담당하는 수리기술자 자격시험에 응시하고자
		 * 하는 자는 건축사법에 따른 건축사 자격을 가진 자 이어야 한다.
		 */
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).getNum()==701) {
				applyPossible=true; break;
			}
		}
		return applyPossible;
	}
	
	public List<methodVO> getMoonWha(int cerNum ,userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond = false;
		String condmes = null;
		int [] etc = {662, 663,	665, 666, 667};
		if(cerNum!=664) {
			for(int i=0; i<etc.length; i++) {
				if(etc[i]==cerNum) {
					cond = gukjun_munhwajae_gisulja(uvo, careerMap, user_eduList, cfvo, user_certiList);
					condmes = "1. 문화재수리 분야에 1년 이상 종사한 사람 \n"
							+" 2. 초,중등교육법에 따른 중학교의 졸업자 또는 이와 같은 수준 이상의 학력이 있다고 인정되는 사람 \n"
							+" 3. 국가기술자격법에 따른 기능사 이상의 자격을 취득한 사람 \n"
							+" 4. 문화재수리기능자";
					mvo.setMess(condmes); mvo.setPossible(cond);
					checkList.add(mvo);
				}
			}
		}else if(cerNum==664) {
			cond=gukjun_munhwa_gisulja_silchk(uvo, careerMap, user_eduList, cfvo, user_certiList);
			condmes="1. 문화재 수리를 위한 실측 및 설계도서의 작성 업무를 담당하는 수리기술자 자격시험에 응시하고자 하는자는"
					+" 건축사법에 따른 건축사 자격을 가진 자이어야 한다.";
			mvo.setMess(condmes); mvo.setPossible(cond);
			checkList.add(mvo);
		}
		return checkList;
	}
	
	/* 변리사 670
	 * ※변리사법 제4조 제3호 중 미성년자는 제외
	 * 1. 금고 이상의 실형을 선고받고 그 집행이 끝나거나(집행이 끝난 것으로 보는 경우를 포함한다) 집행이 면제된 날부터 3년이 지나지 아니한 사람
	 * 2. 금고 이상의 형의 집행유예를 선고받고 그 유예기간 중에 있는 사람
	 * 3. 미성년자, 피한정후견인 또는 피성년후견인
	 * 4. 파산선고를 받고 복권되지 아니한 사람
	 * 5. 다음 각목의 어느 하나에 해당하는 사람
	 * 	가. 탄핵 또는 징계처분에 따라 파면 또는 해임된 사람
	 * 	나. 강등 또는 정직처분을 받은 후 2년이 지나지 아니한 사람
	 * 	다. 이 법에 따른 징계처분으로 등록취소된 후 2년이 지나지 아니한 사람
	 *	라. 「변호사법」에 따라 제명된 후 2년이 지나지 아니한 사람
	 */
	public boolean gukjun_byunlisa(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList, boolean [] check) {
		if(check!=null) {
			for(int i=0; i<check.length; i++) {
				if(check.equals(false)) break;
				else applyPossible=true;
			}
		}
		return applyPossible;
	}
	
	public List<methodVO> getByunRi(userVO uvo, HashMap<Integer, Long> careerMap, 
			List<userEduVO> user_eduList, CertifyVO cfvo, List<userCertiVO> user_certiList) {
		List<methodVO> checkList = new ArrayList<methodVO>();
		methodVO mvo = new methodVO();
		boolean cond = false;
		String condmes = "※변리사법 제4조 제3호 중 미성년자는 제외\r\n" + 
				" 1. 금고 이상의 실형을 선고받고 그 집행이 끝나거나(집행이 끝난 것으로 보는 경우를 포함한다) 집행이 면제된 날부터 3년이 지나지 아니한 사람\r\n" + 
				" 2. 금고 이상의 형의 집행유예를 선고받고 그 유예기간 중에 있는 사람\r\n" + 
				" 3. 미성년자, 피한정후견인 또는 피성년후견인\r\n" + 
				" 4. 파산선고를 받고 복권되지 아니한 사람\r\n" + 
				" 5. 다음 각목의 어느 하나에 해당하는 사람\r\n" + 
				" 	가. 탄핵 또는 징계처분에 따라 파면 또는 해임된 사람\r\n" + 
				"    나. 강등 또는 정직처분을 받은 후 2년이 지나지 아니한 사람\r\n" + 
				"    다. 이 법에 따른 징계처분으로 등록취소된 후 2년이 지나지 아니한 사람\r\n" + 
				"    라. 「변호사법」에 따라 제명된 후 2년이 지나지 아니한 사람";
		mvo.setMess(condmes); mvo.setPossible(cond);
		checkList.add(mvo);
		return checkList;
	}
	
	
	
}
