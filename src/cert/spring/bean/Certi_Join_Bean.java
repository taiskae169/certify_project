package cert.spring.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.readCSV.test.CSVtoMap;

@Controller
@RequestMapping("/user_join/")
public class Certi_Join_Bean {
	
	ModelAndView mv =null;
	
	@RequestMapping("test1.certi")
	public ModelAndView test1(){
		mv = new ModelAndView();
		mv.setViewName("/test1");
		return mv;
	}
	
	@SuppressWarnings("null")
	@RequestMapping("test_uni.certi")
	public ModelAndView test_test(String search) throws IOException {
		mv = new ModelAndView();
		CSVtoMap ctm = new CSVtoMap();
		HashMap<String, Set<String>> univercity = ctm.reader("C:/Users/DELL/Documents/major.csv");

		String [] uni_name = null;
		if(search!=null) {
			int i = 0;
	        for (Entry<String, Set<String>> e : univercity.entrySet()) {
	        	if (e.getKey().contains(search)) {
	        	  uni_name[i]=e.getKey();
	        	}
	        }
		}
		mv.addObject("uni_name",uni_name);
		mv.setViewName("/test_uni");
		return mv;
	}
	
	
}
