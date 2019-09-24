package cert.spring.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import certify.vo.*;
import certify.cond.method.*;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.CSVReader;

import board.vo.BoardVO;
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
	public ModelAndView mainpage(HttpSession session,Model model) {

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
			
			
			mv.addObject("naverURI",naverURI);
			//네이버 로그인을 위한 URL 설정 끝
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String id = (String)session.getAttribute("sessionID");
		System.out.println("sessionID : " +id);
		
		if(id!=null) {
			System.out.println("세션 아이디 있음");
			String name = userdao.getName(id);
			session.setAttribute("name", name);
			mv.addObject("name",name);
		}
		
			int boardcount = (Integer)sql.selectOne("board.getCountALL");
			List<BoardVO> boardlist = null;
		
			HashMap<Object, Object> parameters = new HashMap<Object, Object>();
			parameters.put("start", 1);
			parameters.put("end", 7);
		
	    	boardlist = (List)sql.selectList("board.getAriticleALL", parameters);
	    	
			model.addAttribute("boardlist", boardlist);
			model.addAttribute("boardcount", boardcount);
			
			
			
			
			int nocount = (Integer)sql.selectOne("board.getCountCate",9);
			List<BoardVO> noticeboardlist = null;
			
			
		    noticeboardlist = (List)sql.selectList("board.getAriticleNotice");
			model.addAttribute("noboardlist", noticeboardlist);
			model.addAttribute("nocount",nocount);
		
			
		mv.setViewName("/main/main");
		return mv;
	}

	//로그인 모듈 테스트만을 위한 메소드
	@RequestMapping("logintest.certi")
	public ModelAndView logintest() {
		mv = new ModelAndView();
		
		
		mv.setViewName("/main/loginbox");
		return mv;
	}
	

	

	@RequestMapping("Bongtest.certi")
	public String Bongtest() throws IOException, SQLException{
		 
		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("/Users/mac/certification.csv"), "EUC-KR")); // CSV파일 한글로 읽어들이기
		
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			CertificationVO vo = new CertificationVO();
			vo.setNum(Integer.parseInt(nextLine[0]));
			vo.setName(nextLine[1]);
			vo.setCate(1);
			vo.setType(1);
			sql.insert("user.insertMember", vo);
			
		} 
		
		int bong = sql.selectOne("Test");
		System.out.println(bong);
		
		return "/main/main";
	}

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
	

