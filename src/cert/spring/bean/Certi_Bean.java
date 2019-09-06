package cert.spring.bean;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.http.HttpSession;

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
	public ModelAndView mainpage(HttpSession session) {
		mv = new ModelAndView();
		
		String redirectURI;
		String naverURI;
		String state;
		try {
			redirectURI = URLEncoder.encode("http://localhost:8080/certify/loginCh.certi", "UTF-8");
			SecureRandom rendom = new SecureRandom();
			state = new BigInteger(130,rendom).toString();
			
			naverURI="https://nid.naver.com/oauth2.0/authorize?response_type=code";
			naverURI +="&client_id=UfsHgM0aSD0KyYettfN3";
			naverURI +="&redirect_uri=" + redirectURI;
			naverURI +="&state"+state;
			
			session.setAttribute("naverState", state);
			
			
			mv.addObject("naverURI",naverURI);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

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
