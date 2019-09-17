package cert.spring.bean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import certify.user.dao.UserMethod;
import certify.vo.Cer_CategoryVO;
import test.readCSV.test.ReadCSVatUniv;
import test.readCSV.test.VOforList;
import user.vo.userCareerVO;
import user.vo.userEduVO;

@Repository
@Controller
@RequestMapping("/")
public class test_Certi_Join_Bean {
	
	ModelAndView mv =null;
	
	@Autowired
	UserMethod userdao = null;
	
	@Autowired
	userCareerVO ucvo = null;
	
	@Autowired
	userEduVO uevo = null;
	
	@RequestMapping("test1.certi")
	public ModelAndView test1(HttpServletRequest request, HttpSession session){
		mv = new ModelAndView();
		
		String testId = "haha3423";
		
		List<Cer_CategoryVO> category = userdao.getCerti_Category();
		mv.addObject("category",category);
		
		mv.setViewName("/test_user_join/test1");
		return mv;
	}
	
	@RequestMapping("test_uni.certi")
	public ModelAndView test_test(int num, String school_name, String major_name, 
							String school_nameFix, String major_nameFix) throws IOException {
		mv = new ModelAndView();
		String filepath = "C:/Users/DELL/Documents/major.csv";
		ReadCSVatUniv rcu = new ReadCSVatUniv();
		HashMap<String, Set<String>> univercity = rcu.csvToMap(filepath);

		// 활용변수들
		List uni_List = null;
		List<Object> major_List = null;
		int length=0;
		String years = null;
		Object [] majorArr = null;
		int edu=0;		// default = 0 : 고등학교
		String eduType=null;
		int major=0;
		
		if(school_name!=null) {
			uni_List  = new ArrayList();
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
				major_List.add("문/이과, 실업계 통합");
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
			major_List =  new ArrayList<Object>();
			majorArr = univercity.get(school_nameFix).toArray();
			for(int i=0; i<majorArr.length; i++) {
				if(((String) majorArr[i]).trim().contains(major_name)) {
					major_List.add(majorArr[i]);
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
		mv.setViewName("/test_user_join/test_uni");
		return mv;
	}
	
	@RequestMapping(value="testEC_Pro.certi", method = RequestMethod.POST)
	public ModelAndView testEC_Pro(HttpSession session, String eduList, String careerList) throws IOException, Exception{
		mv = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//String id = (String) session.getAttribute("sessionID");
		String id = "test";
		
		List<userEduVO> eduVoList = new ArrayList<userEduVO>();
		List<userCareerVO> careerVoList = new ArrayList<userCareerVO>();		
		
		String pre_edu_enc = URLDecoder.decode(eduList, "UTF-8");
		String pre_career_enc = URLDecoder.decode(careerList, "UTF-8");
		
		String [] edu_enc = pre_edu_enc.split("@@");
		for(int i=0; i< edu_enc.length; i++) {
			uevo = new userEduVO();
			String[] eduArr = edu_enc[i].split("&");
			for(int j=0; j<eduArr.length; j++) {
				eduArr[j] = eduArr[j].substring(eduArr[j].indexOf("=")+1);
				System.out.println(eduArr[j]);
			}
			uevo.setId(id);
			uevo.setEdu_name(eduArr[0]);
			uevo.setMajor_name(eduArr[1]);
			// eduArr[2] = eduType==VO에없는 것
			uevo.setState(Integer.parseInt(eduArr[3]));
			uevo.setMajor(Integer.parseInt(eduArr[4]));
				Date ent_date = sdf.parse(eduArr[5]);
				Date gra_date = sdf.parse(eduArr[6]);
			uevo.setEnt_date(ent_date);
			uevo.setGra_date(gra_date);
			uevo.setEdu(Integer.parseInt(eduArr[7]));
			userdao.insertUserEdu(uevo);
			eduVoList.add(uevo);
			// 주 : 배열의 순서가 바뀔 일이 없기 때문에 가능한 하드코딩입니다. 맵핑으로 못받더라구요 ㅜㅜㅜ
		}
		
		String [] career_enc = pre_career_enc.split("@@");
		for(int i=0; i< career_enc.length; i++) {
			ucvo = new userCareerVO();
			String [] careerArr = career_enc[i].split("&");
			for(int j=0; j<careerArr.length; j++) {
				careerArr[j] = careerArr[j].substring(careerArr[j].indexOf("=")+1);
			}
			ucvo.setId(id);
			ucvo.setCompany(careerArr[0]);
			ucvo.setComp_cate(Integer.parseInt(careerArr[1]));
				Date ent_date = sdf.parse(careerArr[2]);
				Date gra_date = sdf.parse(careerArr[3]);
			ucvo.setCom_ent_date(ent_date);
			ucvo.setCom_gra_date(gra_date);
			userdao.insertUserCareer(ucvo);
			careerVoList.add(ucvo);
		}

		mv.setViewName("/test_user_join/testEC_Pro");
		return mv;
	}
	
	
}
