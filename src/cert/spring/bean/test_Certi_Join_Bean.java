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

import test.readCSV.test.CSVtoMap;

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
	public ModelAndView test_test(String school_name, String school_nameFix, String major_nameFix) throws IOException {
		mv = new ModelAndView();
		CSVtoMap ctm = new CSVtoMap();
		HashMap<String, Set<String>> univercity = ctm.reader("C:/Users/DELL/Documents/major.csv");

		List uni_name = null;
		List major_name = null;
		int length=0;
		if(school_name!=null) {
			uni_name  = new ArrayList();
			int i = 0;
	        for (Entry<String, Set<String>> e : univercity.entrySet()) {
	        	if (e.getKey().contains(school_name)) {
	        	  uni_name.add(e.getKey());
	        	  length=uni_name.size();
	        	}
	        }
		}
		if(school_nameFix!=null) {
			System.out.println(school_nameFix);
			major_name =  new ArrayList();
			Object [] majorArr = univercity.get(school_nameFix).toArray();
			for(int i=0; i<majorArr.length; i++) {
				major_name.add(majorArr[i]);
			}
			length=major_name.size();
		}
		if(major_nameFix!=null) {
			System.out.println(school_nameFix);
			major_name =  new ArrayList();
			Object [] majorArr = univercity.get(school_nameFix).toArray();
			for(int i=0; i<majorArr.length; i++) {
				major_name.add(majorArr[i]);
			}
			length=major_name.size();
		}
		mv.addObject("uni_name",uni_name);
		mv.addObject("uni_name_length",length);
		mv.addObject("major_name",major_name);
		mv.addObject("major_name_length",length);
		mv.setViewName("/test_user_join/test_uni");
		return mv;
	}
	
	
}
