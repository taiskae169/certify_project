package cert.spring.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.readCSV.test.ReadCSVatUniv;
import test.readCSV.test.VOforList;

@Repository
@Controller
@RequestMapping("/")
public class test_Certi_Join_Bean {
	
	ModelAndView mv =null;
	
	@RequestMapping("test1.certi")
	public ModelAndView test1(){
		mv = new ModelAndView();
		mv.setViewName("/test_user_join/test1");
		return mv;
	}
	
	@RequestMapping("test_uni.certi")
	public ModelAndView test_test(int num, String school_name, String school_nameFix, String major_nameFix) throws IOException {
		mv = new ModelAndView();
		String filepath = "C:/Users/DELL/Documents/major.csv";
		ReadCSVatUniv rcu = new ReadCSVatUniv();
		HashMap<String, Set<String>> univercity = rcu.csvToMap(filepath);

		// 활용변수들
		List uni_List = null;
		List<Object> major_name = null;
		int length=0;
		String years = null;
		Object [] majorArr = null;
		int edu=0;		// default = 0 : 고등학교
		String eduType=null;
		
		if(school_name!=null) {
			uni_List  = new ArrayList();
			int i = 0;
	        for (Entry<String, Set<String>> e : univercity.entrySet()) {
	        	if (e.getKey().contains(school_name)) {
	        		uni_List.add(e.getKey());
	        	  length=uni_List.size();
	        	}
	        }
		}
		if(school_nameFix!=null) {
			major_name =  new ArrayList<Object>();
			majorArr = univercity.get(school_nameFix).toArray();
			for(int i=0; i<majorArr.length; i++) {
				major_name.add(majorArr[i]);
			}
			length=major_name.size();
		}
		
		if(school_nameFix!=null && major_nameFix!=null) {
			System.out.println(school_nameFix+" "+major_nameFix);
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
						eduType = univList.get(i).getEduType();
						break checkMajor;
					}
				}
		}
		mv.addObject("num",num);
		mv.addObject("school_nameFix", school_nameFix);
		mv.addObject("uni_List",uni_List);
		mv.addObject("uni_List_length",length);
		mv.addObject("major_name",major_name);
		mv.addObject("major_name_length",length);
		mv.addObject("major_years", years);
		mv.addObject("eduType", eduType);
		mv.addObject("edu", edu);
		mv.setViewName("/test_user_join/test_uni");
		return mv;
	}
	
	
}
