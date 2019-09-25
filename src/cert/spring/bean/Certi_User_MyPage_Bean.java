	package cert.spring.bean;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.services.calendar.Calendar;

import certify.user.dao.UserMethod;
import certify.vo.Cer_CategoryVO;
import certify.vo.CertifyVO;
import test.readCSV.test.ReadCSVatUniv;
import test.readCSV.test.VOforList;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;
import user.vo.userVO;
import user.vo.user_Edu_edu_valueVO;
import user.vo.user_info_qual_value;

@Repository
@Controller
@RequestMapping("/user/mp/")
public class Certi_User_MyPage_Bean {
	
	ModelAndView mv =null;
	
	@Autowired
	UserMethod userdao = null;
	@Autowired
	userCareerVO ucvo = null;
	@Autowired
	userCertiVO uctvo = null;
	@Autowired
	userEduVO uevo = null;
	
	@RequestMapping("myPage.certi")
	public ModelAndView myPage(HttpSession session, HttpServletRequest request) {
		mv = new ModelAndView();	
		if(session.getAttribute("sessionID")!=null) {
			userVO uvo = userdao.getUserInfo((String) session.getAttribute("sessionID"));
			List<user_Edu_edu_valueVO>edu_value = userdao.getUser_Edu_Val();
			
			List<CertifyVO> cf = userdao.getAllCertify();
			String id = (String)session.getAttribute("sessionID");
			
			List<Cer_CategoryVO> certi_cate = userdao.getCerti_Category();
			List<userEduVO> eduList = userdao.getUserEdu(id);
			if(eduList!=null) mv.addObject("eduList", eduList);	
			
			List<userCareerVO> careerList = userdao.getUserCareer(id);
			if(careerList!=null) mv.addObject("careerList", careerList);
			
			List<userCertiVO> certiList = userdao.getUserCerti(id);
			if(certiList!=null) mv.addObject("certiList", certiList);
			
			List<user_info_qual_value> quals = userdao.getQual();
			mv.addObject("quals",quals);
			
			mv.addObject("edu_value",edu_value);
			mv.addObject("allCerti", cf);
			mv.addObject("certi_cate", certi_cate);	
			mv.addObject("uvo",uvo);
		}
		if(request.getParameter("type")!=null) mv.addObject("type", request.getParameter("type"));
		mv.setViewName("/user_myPage/myPage");
		return mv;
	}
	
	@RequestMapping("myPage_data.certi")
	public ModelAndView myPage_data(HttpSession session) {
		mv = new ModelAndView();
		
		mv.addObject("sessionID", (String) session.getAttribute("sessionID"));
		mv.setViewName("/user_myPage/myPage_data");
		return mv;
	}
	

	@RequestMapping("/myPage_modify.certi")
	public ModelAndView myPage_modify() {
		mv = new ModelAndView();
		
		mv.setViewName("/user_myPage/myPage_modify");
		return mv;
	}
	
	@RequestMapping(value="/myPage_modifyPro.certi", method = RequestMethod.POST)
	public ModelAndView modifyUserData(HttpSession session, String user_info) throws Exception {
		mv = new ModelAndView();
		String id = (String)session.getAttribute("sessionID");
		
		if(user_info!=null) {
			String user_infoEnc = URLDecoder.decode(user_info, "UTF-8");
			String [] user_infoArr = user_infoEnc.split("&");
			for(int i = 0; i<user_infoArr.length; i++) {
				user_infoArr[i] = user_infoArr[i].substring(user_infoArr[i].indexOf("=")+1);
				// [0] : pw, [1] : wana, [2] : qual
			}
			userVO uvo = new userVO();
			uvo.setId(id); 
			uvo.setPw(user_infoArr[0]); 
			uvo.setWana(Integer.parseInt(user_infoArr[1]));
			uvo.setQual(Integer.parseInt(user_infoArr[2]));
			userdao.updateUser_Info(uvo);
		}
		
		mv.setViewName("/user_myPage/myPage_modifyPro");
		return mv;
	}
	
	@RequestMapping("/myPage_delete.certi")
	public ModelAndView myPage_delete_edu(String userEdu, String userCareer, String userCerti,
			HttpSession session) throws Exception {
		mv = new ModelAndView();
		
		userEduVO uevo = new userEduVO();
		userCareerVO ucrvo = new userCareerVO();
		userCertiVO uctvo = new userCertiVO();
		
		String id = (String)session.getAttribute("sessionID");
		if(userEdu!=null) {
			uevo.setId(id);
			String userEduEnc = URLDecoder.decode(userEdu, "UTF-8");
			String [] userEduArr = userEduEnc.split("&");
			for(int i = 0; i<userEduArr.length; i++) {
				userEduArr[i] = userEduArr[i].substring(userEduArr[i].indexOf("=")+1);
				// [0] : edu_name, [1] : major_name, [2] : edu, [3] : major, [4] : state
				// [5] : ent_Date, [6] : gra_Date
			}
			uevo.setEdu_name(userEduArr[0]);
			uevo.setMajor_name(userEduArr[1]);
			uevo.setEdu(Integer.parseInt(userEduArr[2]));
			uevo.setMajor(Integer.parseInt(userEduArr[3]));
			uevo.setState(Integer.parseInt(userEduArr[4]));
			userdao.deleteEdu(uevo);	
		}else if(userCareer!=null) {
			ucrvo.setId(id);
			String userCareerEnc = URLDecoder.decode(userCareer, "UTF-8");
			String [] userCareerArr = userCareerEnc.split("&");
			for(int i = 0; i<userCareerArr.length; i++) {
				userCareerArr[i] = userCareerArr[i].substring(userCareerArr[i].indexOf("=")+1);
				// [0] : edu_name, [1] : major_name, [2] : edu, [3] : major, [4] : state
				// [5] : ent_Date, [6] : gra_Date
			}
			ucrvo.setCompany(userCareerArr[0]);
			ucrvo.setComp_cate(Integer.parseInt(userCareerArr[1]));
			userdao.deleteCareer(ucrvo);	
		}else if(userCerti!=null) {
			uctvo.setId(id);
			String userCertiEnc = URLDecoder.decode(userCerti, "UTF-8");
			String [] userCertiArr = userCertiEnc.split("&");
			for(int i = 0; i<userCertiArr.length; i++) {
				userCertiArr[i] = userCertiArr[i].substring(userCertiArr[i].indexOf("=")+1);
				// [0] : edu_name, [1] : major_name, [2] : edu, [3] : major, [4] : state
				// [5] : ent_Date, [6] : gra_Date
			}
			uctvo.setCer_name(Integer.parseInt(userCertiArr[0]));
			uctvo.setType(Integer.parseInt(userCertiArr[1]));
			userdao.deleteCerti(uctvo);	
		}
		mv.setViewName("/user_myPage/myPage_delete");
		return mv;
	}

	
	@RequestMapping("input_eduCareer.certi")
	public ModelAndView input_eduCareer(HttpServletRequest request, HttpSession session){
		mv = new ModelAndView();
		String id = (String) session.getAttribute("sessionID");
		List<Cer_CategoryVO> category = userdao.getCerti_Category();
		mv.addObject("category",category);
		List<CertifyVO> certify = userdao.getAllCertify();
		mv.addObject("certify",certify);
		
		
		mv.addObject("id",id);
		mv.setViewName("/user_myPage/input_eduCareer");
		return mv;
	}
	
	@RequestMapping("input_eduCareerPage.certi")
	public ModelAndView input_eduCareerPage(HttpServletRequest request, HttpSession session){
		mv = new ModelAndView();
		
		mv.setViewName("/user_myPage/input_eduCareerPage");
		return mv;
	}
	
	@RequestMapping("eduChooseProcess.certi")
	public ModelAndView eduChooseProcess(HttpServletRequest request, int num, String school_name, String major_name, 
							String school_nameFix, String major_nameFix) throws IOException, Throwable {
		mv = new ModelAndView();
		
		String csvPath = request.getSession().getServletContext().getRealPath("/csvSource/major.csv");
		String filepath = new File(csvPath).getAbsolutePath();
		ReadCSVatUniv rcu = new ReadCSVatUniv();
		HashMap<String, Set<String>> univercity = rcu.csvToMap(filepath);

		// 활용변수들
		List<String> uni_List = null;
		List<Object> major_List = null;
		int length=0;
		String years = null;
		Object [] majorArr = null;
		int edu=0;		// default = 0 : 고등학교
		String eduType=null;
		int major=0;
		
		if(school_name!=null) {
			uni_List  = new ArrayList<String>();
			if(school_name.contains("고등학교")) {
				uni_List.add(school_name);
				length=uni_List.size();
			}else {
		        for (Entry<String, Set<String>> e : univercity.entrySet()) {
		        	if (e.getKey().contains(school_name)) {
		        		uni_List.add(e.getKey());
		        	  length=uni_List.size();
		        	}
		        }
			}
		}
		
		if(school_nameFix!=null && major_name==null) {
			if(school_nameFix.contains("고등학교")) {
				major_List=new ArrayList<Object>();
				major_List.add("문/이과/실업계 (통합)");
				length=major_List.size();
			}else {
				major_List =  new ArrayList<Object>();
				majorArr = univercity.get(school_nameFix).toArray();
				for(int i=0; i<majorArr.length; i++) {
					major_List.add(majorArr[i]);
				}
				length=major_List.size();
			}
		}else if(school_nameFix!=null && major_name!=null) {
			if(school_nameFix.contains("고등학교")) {
				major_List=new ArrayList<Object>();
				major_List.add(major_name);
				length=major_List.size();
			}else {
				major_List =  new ArrayList<Object>();
				majorArr = univercity.get(school_nameFix).toArray();
				for(int i=0; i<majorArr.length; i++) {
					if(((String) majorArr[i]).trim().contains(major_name)) {
						major_List.add(majorArr[i]);
					}
				}
			}
			length=major_List.size();
		}
		
		if(school_nameFix!=null && major_nameFix!=null) {
			if(school_nameFix.contains("고등학교")) {
				edu = 0;
				eduType = "고등학교";
				major = 999;
			}else {
				List<VOforList> univList = rcu.csvToList(filepath);
				checkMajor : 
					for(int i=0; i<univList.size(); i++) {
						if(univList.get(i).getUnivName().equals(school_nameFix) && univList.get(i).getMajorName().equals(major_nameFix)) {
							years = univList.get(i).getYears();
							switch(univList.get(i).getEduType()) {
								case "전문학사" : edu = 1; break;
								case "학사" : edu = 3; break;
								case "학석사통합" : edu = 10; break;
								case "석사" : edu = 10; break;
								case "박사" : edu = 11; break;
								case "석박사통합" : edu = 11; break;
								case "석사, 박사" : edu = 11; break;
								case "박사, 석박사통합" : edu = 11; break;
								case "석사, 석박사통합" : edu = 10; break;
								case "석사,박사, 석박사통합" : edu = 11; break;
							}
							eduType = univList.get(i).getEduType()+" "+univList.get(i).getYears();
							break checkMajor;
						}	
					}
			}
		}
		mv.addObject("num",num);
		mv.addObject("school_nameFix", school_nameFix);
		mv.addObject("uni_List",uni_List);
		mv.addObject("uni_List_length",length);
		mv.addObject("major_List",major_List);
		mv.addObject("major_List_length",length);
		mv.addObject("major_years", years);
		mv.addObject("major", major);
		mv.addObject("eduType", eduType);
		mv.addObject("edu", edu);
		mv.setViewName("/user_myPage/eduChooseProcess");
		return mv;
	}
	
	@RequestMapping("Pro_careerInput.certi")
	public ModelAndView Pro_careerInput(int num, String certi_name, String searchHelp) throws IOException {
		mv = new ModelAndView();
		// 활용변수들
		List<CertifyVO> certify = userdao.getAllCertify();
		List<String> certi_list = null;
		
		if(certi_name!=null) {
			if(searchHelp.equals("all")) {
				certi_list = new ArrayList<String>();
				for(CertifyVO cf : certify) {
					if(cf.getName().contains(certi_name)) {
						certi_list.add(cf.getName());
					}
				}
			}else if(searchHelp.equals("ss1")) {
				certi_list = new ArrayList<String>();
				for(CertifyVO cf : certify) {
					if(cf.getType()==0 || cf.getType()==1 || 
							cf.getType()==2 || cf.getType()==3 || cf.getType()==4) {
						if(cf.getName().contains(certi_name))certi_list.add(cf.getName());
					}
				}
			}else if(searchHelp.equals("ss2")) {
				certi_list = new ArrayList<String>();
				for(CertifyVO cf : certify) {
					if(cf.getType()==5) {
						if(cf.getName().contains(certi_name))certi_list.add(cf.getName());
					}
				}
			}else if(searchHelp.equals("ss3")) {
				certi_list = new ArrayList<String>();
				for(CertifyVO cf : certify) {
					if(cf.getType()==6) {
						if(cf.getName().contains(certi_name))certi_list.add(cf.getName());
					}
				}
			}else {
				certi_list = new ArrayList<String>();
				for(CertifyVO cf : certify) {
					if(cf.getType()==7) {
						if(cf.getName().contains(certi_name))certi_list.add(cf.getName());
					}
				}
			}
		}
		
		mv.addObject("num",num);
		mv.addObject("certi_list",certi_list);
		mv.setViewName("/user_myPage/Pro_careerInput");
		return mv;
	}
	
	
	@RequestMapping(value="inputUserData_Pro.certi", method = RequestMethod.POST)
	public ModelAndView inputUserData_Pro(HttpSession session, String eduList, String careerList, String certiList) throws IOException, Exception{
		mv = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String id = (String) session.getAttribute("sessionID");	
		
		if(id!=null) {
			// 학력사항 체크
			if(!eduList.equals("notUse")) {
				String pre_edu_enc = URLDecoder.decode(eduList, "UTF-8");
				String [] edu_enc = pre_edu_enc.split("@@");
				for(int i=0; i< edu_enc.length; i++) {
					Date gra_date=null;
					Date ent_date = null;
					uevo = new userEduVO();
					String[] eduArr = edu_enc[i].split("&");
					for(int j=0; j<eduArr.length; j++) {
						eduArr[j] = eduArr[j].substring(eduArr[j].indexOf("=")+1);
					}
					uevo.setId(id);
					uevo.setEdu_name(eduArr[0]);
					uevo.setMajor_name(eduArr[1]);
									// eduArr[2] = eduType== VO에없는 것
					uevo.setState(Integer.parseInt(eduArr[3]));
					uevo.setMajor(Integer.parseInt(eduArr[4]));
						if(eduArr[5]==null || eduArr[5].equals("") || eduArr[5].equals(" ")) {
							ent_date = new Date();
							String time = sdf.format(ent_date);
							ent_date = sdf.parse(time);
						}else {
							ent_date = sdf.parse(eduArr[5]);
						}
						
						if(eduArr[6]==null || eduArr[6].equals("") || eduArr[6].equals(" ")) {
							gra_date = new Date();
							String time = sdf.format(gra_date);
							gra_date = sdf.parse(time);
						}else {
							gra_date = sdf.parse(eduArr[6]);
						}
					uevo.setEnt_date(ent_date);
					uevo.setGra_date(gra_date);
					uevo.setEdu(Integer.parseInt(eduArr[7]));
					userdao.insertUserEdu(uevo);
					// 주 : 배열의 순서가 바뀔 일이 없기 때문에 가능한 하드코딩입니다. 맵핑으로 못받더라구요 ㅜㅜㅜ
				}
			}
			
			// 경력사항
			if(!careerList.equals("notUse")) {
				String pre_career_enc = URLDecoder.decode(careerList, "UTF-8");
				String [] career_enc = pre_career_enc.split("@@");
				for(int i=0; i< career_enc.length; i++) {
					ucvo = new userCareerVO();
					Date gra_date=null;
					Date ent_date = null;
					String [] careerArr = career_enc[i].split("&");
					for(int j=0; j<careerArr.length; j++) {
						careerArr[j] = careerArr[j].substring(careerArr[j].indexOf("=")+1);
					}
					ucvo.setId(id);
					ucvo.setCompany(careerArr[0]);
					ucvo.setComp_cate(Integer.parseInt(careerArr[1]));
						if(careerArr[2]==null || careerArr[2].equals("") || careerArr[2].equals(" ")) {
							ent_date = new Date();
							String time = sdf.format(ent_date);
							ent_date = sdf.parse(time);
						}else {
							ent_date = sdf.parse(careerArr[2]);
						}
						if(careerArr[3]==null || careerArr[3].equals("") || careerArr[3].equals(" ")) {
							gra_date = new Date();
							String time = sdf.format(gra_date);
							gra_date = sdf.parse(time);
						}else {
							gra_date = sdf.parse(careerArr[3]);
						}
					ucvo.setCom_ent_date(ent_date);
					ucvo.setCom_gra_date(gra_date);
					userdao.insertUserCareer(ucvo);
				}
			}
			
			// 자격사항
			if(!certiList.equals("notUse")) {
				String pre_certi_enc = URLDecoder.decode(certiList, "UTF-8");
				String [] certi_enc = pre_certi_enc.split("@@");
				List<CertifyVO> certify = userdao.getAllCertify();
				System.out.println();
				int cate=0;
				int type=0;
				int cer_name=0;
				
				for(int i=0; i< certi_enc.length; i++) {
					uctvo = new userCertiVO();
					Date cer_date = null;
					String [] certiArr = certi_enc[i].split("&");
					for(int j=0; j<certiArr.length; j++) {
						certiArr[j] = certiArr[j].substring(certiArr[j].indexOf("=")+1);
					}
					for(CertifyVO cf : certify) {
						if(cf.getName().equals(certiArr[0])) {
							cate=cf.getCate();
							type=cf.getType();
							cer_name=cf.getNum();
							break;
						}
					}
					uctvo.setId(id);
					uctvo.setCate(cate);
					uctvo.setType(type);
					uctvo.setCer_name(cer_name);					
					if(certiArr[1]==null || certiArr[1].equals("") || certiArr[1].equals(" ")) {
						cer_date = new Date();
						String time = sdf.format(cer_date);
						cer_date = sdf.parse(time);
					}else {
						cer_date = sdf.parse(certiArr[1]);
					}
					uctvo.setCer_date(cer_date);	
					userdao.insertUserCertify(uctvo);
				}
			}
			
		}
		mv.setViewName("/user_myPage/inputUserData_Pro");
		return mv;
	}
	
//	@RequestMapping("/caltest.certi")
//	public ModelAndView caltest() {
//		mv = new ModelAndView();
////		일정 테스트 페이지
//		mv.setViewName("/user_myPage/calTest");
//		return mv;
//	}
	
//	@RequestMapping("/calteee.certi")
//	public ModelAndView caltest2() {
//		mv = new ModelAndView();
//
//		//일정 테스트 페이지
//		mv.setViewName("/user_myPage/calTest2");
//		return mv;
//	}
	
	
	@RequestMapping("/callist.certi")
	public ModelAndView CalList() {
		mv = new ModelAndView();

		
		//일정 리스트 출력하는 페이지
		mv.setViewName("/user_myPage/callist");
		return mv;
	}//일정 리스트를 위한 페이지
	
	
	
}
