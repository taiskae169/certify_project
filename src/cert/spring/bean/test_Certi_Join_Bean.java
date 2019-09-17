package cert.spring.bean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
			System.out.println(school_nameFix+" "+major_nameFix);
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
	@ResponseBody
	public ModelAndView testEC_Pro(String eduList, String careerList) throws IOException{
		mv = new ModelAndView();
		String pre_edu_enc = URLDecoder.decode(eduList, "UTF-8");
		String pre_career_enc = URLDecoder.decode(careerList, "UTF-8");
		
		String [] edu_enc = pre_edu_enc.split("@@");
		for(int i=0; i< edu_enc.length; i++) {
			System.out.println(edu_enc[i]);
		}
		// ajax로 데이터 잘 넘어옴
		
		
		mv.setViewName("/test_user_join/testEC_Pro");
		return mv;
	}
	
	
}
