package cert.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import certify.user.dao.UserMethod;

import test.readCSV.test.ReadCSVFile_saveToHashSet;


@Controller
@RequestMapping("/")
public class Certi_Bean {

	@Autowired
	UserMethod userdao = null;
	
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
		System.out.println("loginPro ½ÃÀÛ");
		System.out.println(id);
		System.out.println(pw);
		
		int i = (Integer)userdao.logincheck(id, pw);
		
		
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
//	ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ï½ï¿½ ï¿½ï¿½ï¿½ï¿½
	
}
