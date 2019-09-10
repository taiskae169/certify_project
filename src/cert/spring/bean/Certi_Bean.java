package cert.spring.bean;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import certify.user.dao.UserMethod;

@Repository
@Controller
@RequestMapping("/")
public class Certi_Bean {

	@Autowired
	UserMethod userdao = null;
	
	ModelAndView mv =null;
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("main.certi")
	public ModelAndView mainpage(HttpSession session) {

		mv = new ModelAndView();


		mv = new ModelAndView();

		mv = new ModelAndView();


		
		String redirectURI;
		String naverURI;
		String state;
		try {
			//네이버 로그인을 위한 URL 설정
			redirectURI = URLEncoder.encode("http://localhost:8080/certify/user/naverLogin.certi", "UTF-8");
			SecureRandom rendom = new SecureRandom();
			state = new BigInteger(130,rendom).toString();
			
			naverURI="https://nid.naver.com/oauth2.0/authorize?response_type=code";
			naverURI +="&client_id=UfsHgM0aSD0KyYettfN3";
			naverURI +="&redirect_uri=" + redirectURI;
			naverURI +="&state="+state;
			
			session.setAttribute("naverState", state);
			
			
			mv.addObject("naverURI",naverURI);
			//네이버 로그인을 위한 URL 설정 끝
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		mv.setViewName("/main/main");
		return mv;
	}

	//로그인 모듈 테스트만을 위한 메소드
	@RequestMapping("logintest.certi")
	public ModelAndView logintest() {
		mv = new ModelAndView();
		
		System.out.println("test");
		mv.setViewName("/main/loginbox");
		return mv;
	}
	

	

	@RequestMapping("Bongtest.certi")
	public String Bongtest() {
		
		int bong = sql.selectOne("Test");
		
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
//	占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占싹쏙옙 占쏙옙占쏙옙
	
}
