package cert.spring.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.readCSV.test.ReadCSVFile_saveToHashSet;

@Controller
@RequestMapping("/")
public class Certi_Bean {

	ModelAndView mv =null;
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("main.certi")
	public ModelAndView mainpage() {
		mv = new ModelAndView();
		

		mv.setViewName("/main/main");
		return mv;
	}
	
	@RequestMapping("test1.certi")
	public ModelAndView test1(){
		mv = new ModelAndView();
		mv.setViewName("/test1");
		return mv;
	}
	
	@RequestMapping("test_test.certi")
	public ModelAndView test_test() throws IOException {
		mv = new ModelAndView();
		ReadCSVFile_saveToHashSet rcsv = new ReadCSVFile_saveToHashSet();
		List univercity = rcsv.reader("C:/Users/DELL/Documents/major.csv");
		
		
		mv.addObject("uni_name",univercity.get(0));
		mv.addObject("major_name",univercity.get(1));
		mv.setViewName("/test_test");
		return mv;
	}
	
	@RequestMapping("logintest.certi")
	public ModelAndView logintest() {
		mv = new ModelAndView();
		

		mv.setViewName("/main/loginbox");
		return mv;
	}
	@RequestMapping("loginPro.certi")
	public ModelAndView loginPro(String id, String pw) {
		mv = new ModelAndView();
		System.out.println("loginPro ����");
		System.out.println(id);
		System.out.println(pw);
		
		mv.setViewName("/login/loginPro");
		return mv;
	}
	
	@RequestMapping("Bongtest.certi")
	public String Bongtest() {
		
		int bong = sql.selectOne("");
		
		return "/test_test";
	}
	
	
	
//	@RequestMapping("error.certi")
//	public ModelAndView errorpage() {
//		mv = new ModelAndView();
//		
//		System.out.println("errorpage");
//		mv.setViewName("error");
//		
//		return mv;
//	}
//	���������� �Ͻ� ����
	
}
