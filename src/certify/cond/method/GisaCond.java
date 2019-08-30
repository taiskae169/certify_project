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
import user.vo.userCareerSub;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userVO;

/*
 * 기사 자격증 조건문
 * 작성일자 : 19.08.27. 작성자 : 조지훈
 */

public class GisaCond extends OverrideSource{
	
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
	
	// 회원이 기보유한 자격증 리스트 리턴을 위한 변수
	private List<userCertiVO> user_certiList = null;
	
	// 회원의 경력사항을 리턴받는 리스트 변수s
	private HashMap<Integer, Long> careerMap = null;
	
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
	
	
	// (오버라이딩) 단일 회원의 전체 정보 가져오기
	@Override
	public void getUserStatus(String id) {
		// TODO Auto-generated method stub
		super.getUserStatus(id);
	}
	
	// (오버라이딩) 전체 자격증 중 num에 해당하는 자격증 정보 리턴
	@Override
	public CertifyVO getCertifyStatus(int num) {
		// TODO Auto-generated method stub
		return super.getCertifyStatus(num);
	}
	
	
	// 조건1. 4년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	// 매개변수 - Stirng id : 해당 시험 응시자 id, int certify_num : 응시하고자 하는 자격증 번호
	public boolean gisa_cond1(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		
		// 4년제 이상이고, 전공이 자격증의 분류와 같을때 (재학)
		if(edu>=3 && state==1 && major==cfvo.getCate()) {
			long diff = today.getTime() - ent_date.getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		    
		    // 관련학과 2년 이상 마쳤을 경우(현재기준)
		    if(diffDays>year*2) {
		    	// 입사를 한 상태라면.. (동일 및 유사직무분야에 경력이 있을 경우)	
		    	if(careerMap!=null && ( careerMap.containsKey(major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건2. 5년제 대학 관련학과 1/2이상 마친 후, 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond2(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=4 && state==1 && major==cfvo.getCate()) {
			long diff = today.getTime() - ent_date.getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		    if(diffDays>(year*5)/2) {
		    	if(careerMap!=null && ( careerMap.containsKey(major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
		    	}
			}
		}
		return applyPossible;
	}
		
	// 조건3. 6년제 대학 관련학과 1/2이상 마친 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond3(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=5 && state==1 && major==cfvo.getCate()) {
			long diff = today.getTime() - ent_date.getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		    if(diffDays>year*3) {
		    	if(careerMap!=null && ( careerMap.containsKey(major) || careerMap.containsKey(cfvo.getCate()) )) {
		    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
		    	}
		    }
		}
		return applyPossible;
	}	
	
	// 조건4. 관련학과 2년제 전문대학 졸업후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond4(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=1 && state==0 && major==cfvo.getCate()) {
			if(careerMap!=null && ( careerMap.containsKey(major) || careerMap.containsKey(cfvo.getCate()) )) {
	    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
	    	}
		}
		return applyPossible;
	}
	
	// 조건5. 관련학과 3년제 전문대학 졸업후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond5(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=2 && state==0 && major==cfvo.getCate()) {
			if(careerMap!=null && ( careerMap.containsKey(major) || careerMap.containsKey(cfvo.getCate()) )) {
	    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
	    	}
		}
		return applyPossible;
	}
	
	// 조건6. 관련학과 졸업예정자(4년제)
	public boolean gisa_cond6(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=3 && state==2 && major==cfvo.getCate()) {
			applyPossible=true;
		}
		return applyPossible;
	}

	// 조건7. 관련학과 졸업자(4년제)
	public boolean gisa_cond7(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=3 && state==0 && major==cfvo.getCate()) {
			applyPossible=true;
		}
		return applyPossible;
	}

	// 조건8. 관련학과 전공심화과정의 학사학위 취득예정자
	public boolean gisa_cond8(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=6 && state==2 && major==cfvo.getCate()) {
			applyPossible=true;
		}
		return applyPossible;
	}
	
	// 조건9. 관련학과 전공심화과정의 학사학위 취득자
	public boolean gisa_cond9(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu>=6 && state==0 && major==cfvo.getCate()) {
			applyPossible=true;
		}
		return applyPossible;
	}

	// 조건 10. 기능사 자격 취득 후 동일 및 유사직무분야에서 3년이상 실무에 종사한 자
	public boolean gisa_cond10(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		
		for(int i=0; i<user_certiList.size(); i++) {
			if(user_certiList.get(i).cate==cfvo.cate && user_certiList.get(i).type==0) {
				if(careerMap!=null && careerMap.containsKey(cfvo.getCate()) ) {
		    		if(careerMap.get(cfvo.getCate())>=year*3) applyPossible=true;
		    	}
			}
		}
		return applyPossible;
	}
	
	// 조건 11. 동일 및 유사직무분야에서 4년이상 실무에 종사한 자
	public boolean gisa_cond11(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
    		if(careerMap.get(cfvo.getCate())>=year*4) applyPossible=true;
    	}
		return applyPossible;
	}
	
	// 조건 12. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "기사 수준 기술훈련과정" 이수예정자
	public boolean gisa_cond12(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu==8 && state==2)	applyPossible=true;
		return applyPossible;
	}
	
	// 조건 13. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "기사 수준 기술훈련과정" 이수자
	public boolean gisa_cond13(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu==8 && state==0)	applyPossible=true;
		return applyPossible;
	}
	
	// 조건 14. 동일 및 유사직무분야의 고용노동부령이 정하는 교육훈련기관의 "산업기사 수준 기술훈련과정" 이수 후 동일 및 유사직무분야에서 2년이상 실무에 종사한 자
	public boolean gisa_cond14(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(edu==7 && state==0)	{
			if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
	    		if(careerMap.get(cfvo.getCate())>=year*2) applyPossible=true;
	    	}
		}
		return applyPossible;
	}
	
	// 조건 15. 동일 및 유사 직무분야의 다른 종목 기사 '이상'의 자격을 취득한 자
	public boolean gisa_cond15(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.cate && user_certiList.get(i).type >= cfvo.type) applyPossible=true;
			}
		}
		return applyPossible;
	}
	
	// 조건 16. 산업기사 등급 이상 자격 취득 후 동일 및 유사직무 분야에서 1년 이상 실무에 종사한 자
	public boolean gisa_cond16(String id, int certify_num) {
		getUserStatus(id);
		CertifyVO cfvo = getCertifyStatus(certify_num);
		if(user_certiList!=null) {
			for(int i=0; i<user_certiList.size(); i++) {
				if(user_certiList.get(i).cate==cfvo.cate && user_certiList.get(i).type==1) {
					if(careerMap!=null && careerMap.containsKey(cfvo.getCate())) {
			    		if(careerMap.get(cfvo.getCate())>=year) applyPossible=true;
			    	}
				}
			}
		}
		return applyPossible;
	}

	/*
	 * 조건에 포함되지 않은 항목들
	 * - 외국에서 동일한 종목에 해당하는 자격을 취득한 자
	 * - 학점인정등에관한법률 제7조 규정에 의하여 관련학과 106학점 이상을 인정받은 자
	 */
	
}
