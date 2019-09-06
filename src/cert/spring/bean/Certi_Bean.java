package cert.spring.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.readCSV.test.CSVtoMap;

@Controller
@RequestMapping("/")
public class Certi_Bean {

	ModelAndView mv =null;
	
	@RequestMapping("main.certi")
	public ModelAndView mainpage() {
		mv = new ModelAndView();
		mv.setViewName("/main/main");
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
