package cert.spring.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.vo.BoardVO;
import certify.cond.gukjun.GukJun_sahoebogji_1;
import certify.cond.gukjun.Gukgajunmun_noCond;
import certify.cond.gukjun.Gukjun_jungsooSisul1;
import certify.cond.gukjun.Gukjun_sobangAnjun;
import certify.cond.gukjun.Gukjun_sobangSisul;
import certify.cond.method.GiSulsaCond;
import certify.cond.method.GinunJangCond;
import certify.cond.method.GisaCond;
import certify.cond.method.SanUpCond;
import certify.cond.method.methodVO;
import certify.cond.method.userCareerSub;
import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.Cer_CategoryVO;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;
import user.vo.user_Edu_edu_valueVO;

@Controller
@RequestMapping("/certi/")
public class Certi_Certi_Bean {
	
	ModelAndView mv =null;
	
	@Autowired
	UserMethod userdao = null;
	@Autowired
	CertifyMethod certidao = null;
	
	@Autowired
	userCareerVO ucvo = null;
	@Autowired
	userCertiVO uctvo = null;
	@Autowired
	userEduVO uevo = null;	
	
	@RequestMapping("certi_sc_session1.certi")
	public ModelAndView certi_sc_session1(HttpSession session, HttpServletRequest request) {
		mv = new ModelAndView();
		
		int type = Integer.parseInt(request.getParameter("type"));
		String certi_name = request.getParameter("certi_name");
		mv.addObject("type",type);
		mv.addObject("certi_name",certi_name);
		
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null) pageNum  ="1";
		int count=0;
		if(type==1) { 
			if(certi_name==null) count = certidao.getCount_type1();
			else count = certidao.getCount_typeSpec1(certi_name);
		}else {
			if(certi_name==null) count = certidao.getCount_type1();
			else count = certidao.getCount_typeSpec2(certi_name);
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		parameters.put("start", startRow);
	    parameters.put("end", endRow);
	    
	    List<CertifyVO> certiList = null;
	    if(count>0) {
		    if(certi_name==null) {
		    	if(type==1)certiList = certidao.getCertify_Board1(parameters);
		    	else certiList = certidao.getCertify_Board2(parameters);
		    }else {
		    	parameters.put("certi_name", certi_name); 
		    	if(type==1) certiList = certidao.getCertify_Board_Spec1(parameters);
		    	else certiList = certidao.getCertify_Board_Spec2(parameters);
		    }
		    int pageBlock = 10;
			int pageCount = count/pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage=(int)(currentPage/10)*10+1;
			int endPage = startPage + pageBlock-1;
			if(endPage>pageCount) endPage=pageCount;
			mv.addObject("pageCount",pageCount);
			mv.addObject("startPage",startPage);
			mv.addObject("endPage",endPage);
	    }

	    mv.addObject("type", type);
	    mv.addObject("certiList",certiList);
	    mv.addObject("end", endRow);
	    mv.addObject("start", startRow);
	    mv.addObject("count", count);
		mv.setViewName("/certi/selfCheck/certi_sc_session1");
		return mv;
	}
	
	@RequestMapping("certi_sc_session2.certi")
	public ModelAndView certi_sc_session2(HttpSession session, String cerNum) {
		mv = new ModelAndView();
		String id = (String)session.getAttribute("sessionID");
		mv.addObject("cerNum", cerNum);
		userVO uvo = userdao.getUserInfo(id);
		List<user_Edu_edu_valueVO>edu_value = userdao.getUser_Edu_Val();
		List<Cer_CategoryVO> category = certidao.getCerti_Category();
		mv.addObject("id", id);
		mv.addObject("user_name", uvo.getName());
		mv.addObject("edu_value", edu_value);
		mv.addObject("certi_cate", category);
		
		//if(uevo!=null) userdao.insertUserEdu(uevo);
		
		List<userEduVO> eduList = userdao.getUserEdu(id);
		if(eduList!=null) mv.addObject("eduList", eduList);	
		
		mv.setViewName("/certi/selfCheck/certi_sc_session2");
		return mv;
	}
	
	@RequestMapping("certi_sc_session3.certi")
	public ModelAndView certi_sc_session3(HttpSession session, String cerNum) {
		mv = new ModelAndView();
		String id = (String)session.getAttribute("sessionID");
		mv.addObject("cerNum", cerNum);
		userVO uvo = userdao.getUserInfo(id);
		
		List<userCareerVO> careerList = userdao.getUserCareer(id);
		if(careerList!=null) mv.addObject("careerList", careerList);
		
		List<Cer_CategoryVO> category = certidao.getCerti_Category();
		mv.addObject("id", id);
		mv.addObject("user_name", uvo.getName());
		mv.addObject("certi_cate", category);
		
		//if(uevo!=null) userdao.insertUserEdu(uevo);
		
		mv.setViewName("/certi/selfCheck/certi_sc_session3");
		return mv;
	}
	
	@RequestMapping("certi_sc_session4.certi")
	public ModelAndView certi_sc_session4(HttpSession session, String cerNum) {
		mv = new ModelAndView();
		String id = (String)session.getAttribute("sessionID");
		mv.addObject("cerNum", cerNum);
		userVO uvo = userdao.getUserInfo(id);
		
		List<CertifyVO> cf = userdao.getAllCertify();
		List<userCertiVO> certiList = userdao.getUserCerti(id);
		if(certiList!=null) mv.addObject("certiList", certiList);
		
		List<Cer_CategoryVO> category = certidao.getCerti_Category();
		mv.addObject("id", id);
		mv.addObject("allCerti", cf);
		mv.addObject("user_name", uvo.getName());
		mv.addObject("certi_cate", category);
		
		//if(uevo!=null) userdao.insertUserEdu(uevo);
		
		mv.setViewName("/certi/selfCheck/certi_sc_session4");
		return mv;
	}
	
	@RequestMapping("certi_sc_session5.certi")
	public ModelAndView certi_sc_session5(HttpSession session, String cerNum) {
		mv = new ModelAndView();
		String id = (String)session.getAttribute("sessionID");
		if(id!=null) {
		mv.addObject("cerNum", cerNum);
		int certiNum = Integer.parseInt(cerNum);
		int type = 0;
		
		// 날짜 비교를 위한 변수
		int year = 365;	
		Date today = new Date();
		
		userVO uvo = null;
		List<user_Edu_edu_valueVO> edu_value=null;
		List<CertifyVO> cf = null;
		List<userCertiVO> user_certiList = null;
		List<userEduVO> user_eduList = null;
		List<Cer_CategoryVO> certi_cate = null;
		List<userCertiVO> certiList = null;		
		List<methodVO> checkList = null;
		
		CertifyVO cfvo = certidao.getSpecCertify(certiNum);
		
		GinunJangCond ginun_cond = new GinunJangCond();;
		SanUpCond sanup_cond = new SanUpCond();
		GiSulsaCond gisulsa_cond = new GiSulsaCond();
		GisaCond gisa_cond = new GisaCond();
		Gukgajunmun_noCond gukjun_nocond = new Gukgajunmun_noCond();
		Gukjun_jungsooSisul1 gukjun_jungsoo = new Gukjun_jungsooSisul1();
		GukJun_sahoebogji_1 gukjun_sahoe = new GukJun_sahoebogji_1();
		Gukjun_sobangAnjun gukjun_soAnn = new Gukjun_sobangAnjun();
		Gukjun_sobangSisul gukjun_soSii = new Gukjun_sobangSisul();
		
		// 경력사항 리턴 간 받아올 변수
		long comp_workdays = 0;
		
		// 회원의 경력사항을 리턴받는 리스트 변수
		List<userCareerVO> returnCareer = null;
		List<userCareerSub> user_career_sub = null; 		// 카테고리별 근무년수(근무일수) 총합 후 저장을 위한 리스트 변수
		HashMap<Integer, Long> careerMap = null;		// 실제 조건 비교에 사용되는 Map
		
		if(cerNum!=null) {
			uvo = userdao.getUserInfo((String) session.getAttribute("sessionID"));
			user_eduList = userdao.getUserEdu(id);
			returnCareer = userdao.getUserCareer(id);
			edu_value = userdao.getUser_Edu_Val();
			cf = userdao.getAllCertify();
			
			if(returnCareer!=null) {
				user_career_sub = new ArrayList<userCareerSub>();
				for(int i=0; i<returnCareer.size(); i++) {
					long diff = returnCareer.get(i).com_ent_date.getTime() - returnCareer.get(i).com_gra_date.getTime();
					long diffDays = Math.abs(diff / (24 * 60 * 60 * 1000));	// 양수변환
					comp_workdays = diffDays; // 합산 근무일수
					
					userCareerSub ucs = new userCareerSub();
					ucs.setUser_car_cate(returnCareer.get(i).comp_cate);
					ucs.setUser_sub_workdays(comp_workdays);
					user_career_sub.add(ucs);
				}
				careerMap = new HashMap<Integer, Long>();
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
			
			user_certiList = userdao.getUserCerti(id);
			
			CertifyVO specCerti = certidao.getSpecCertify(certiNum);
			mv.addObject("specCerti",specCerti);
			
			for(CertifyVO c : cf) {
				if(c.getNum() == certiNum) type = c.getType();
			}
			certi_cate = userdao.getCerti_Category();
			
			certiList = userdao.getUserCerti(id);
			if(certiList!=null) mv.addObject("certiList", certiList);
			mv.addObject("edu_value",edu_value);
			mv.addObject("allCerti", cf);
			mv.addObject("certi_cate", certi_cate);	
			mv.addObject("uvo",uvo);
		}
		
		if(type == 0) {
			methodVO mvo = new methodVO();
			mvo.setMess("기능사는 자격제한이 없습니다.");
			mvo.setPossible(true);
			checkList = new ArrayList<methodVO>();
			checkList.add(mvo);
		}else if(type==1) {
			checkList = sanup_cond.getSanupAll(
					uvo, careerMap, user_eduList, cfvo, user_certiList);
		}else if(type==2) {
			checkList = gisa_cond.getGisaAll(
					uvo, careerMap, user_eduList, cfvo, user_certiList);
		}else if(type==3) {
			checkList = gisulsa_cond.getGisulsaAll(
					uvo, careerMap, user_eduList, cfvo, user_certiList);
		}else if(type==4) {
			checkList = ginun_cond.getGinunjangAll(
					uvo, careerMap, user_eduList, cfvo, user_certiList);
		}else if(type==5) {
			if(certiNum==662 || certiNum==663 || certiNum==664 || certiNum==665 || certiNum==666 || certiNum==667) {
				checkList = gukjun_nocond.getMoonWha(certiNum,
						uvo, careerMap, user_eduList, cfvo, user_certiList);
			}else if(certiNum==670) {
				checkList = gukjun_nocond.getByunRi(uvo, careerMap, user_eduList, cfvo, user_certiList);
			}else {
				methodVO mvo = new methodVO();
				mvo.setMess("국가전문자격은 일부 자격증을 제외하곤 자격제한이 없습니다.");
				mvo.setPossible(true);
				checkList = new ArrayList<methodVO>();
				checkList.add(mvo);
			}
		}
		String pass = null;
		for(methodVO mvo : checkList) {
			if(mvo.isPossible()==true) pass="응시 가능";
			else pass="불가능";
		}
		
		mv.addObject("checkList", checkList);
		mv.addObject("pass", pass);
		mv.addObject("id", id);
		}
		mv.setViewName("/certi/selfCheck/certi_sc_session5");
		return mv;
	}
	

	
	
}
