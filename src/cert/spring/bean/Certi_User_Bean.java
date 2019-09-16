package cert.spring.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import certify.user.dao.UserMethod;
import certify.vo.Cer_CategoryVO;
import user.vo.userVO;





@Controller
@RequestMapping("/user/")
public class Certi_User_Bean {
// 유저 관련 페이지를 위한 bean파일
// 로그인/ 개인정보 수정과 같은 페이지를 위한 컨트롤러입니다.
	ModelAndView mv = null;
	
	@Autowired
	UserMethod userdao = null;
	
	@RequestMapping("loginPro.certi")
	public ModelAndView loginPro(String id, String pw) {
		mv = new ModelAndView();

		System.out.println(id);
		System.out.println(pw);
		
		int i = (Integer)userdao.logincheck(id, pw);
		
		
		mv.setViewName("/login/loginPro");
		return mv;
	}//일반적 로그인 체크, 현재 DB테스트로 인하여  간단한 테스트만을 설정하였음, 이후 변경 필요
	
	@RequestMapping("naverLogin.certi")
	public ModelAndView naverLogin(HttpSession session, String code) {
		System.out.println("네이버 로그인 시작");
		mv = new ModelAndView();
		
		String clientId="UfsHgM0aSD0KyYettfN3";
		String clientSecret="jsiBe65lAg";
		String state = (String)session.getAttribute("naverState");
		String redirectURI;
		String apiURL;
		try {
			redirectURI = URLEncoder.encode("http://localhost:8080/certify/user/naverLogin.certi", "UTF-8");
			apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String access_token = "";
		    String refresh_token = "";
		    
		    URL url = new URL(apiURL);
		    HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    con.setRequestMethod("GET");
		    int responseCode=con.getResponseCode();
		    BufferedReader br;
		    
		    
		    System.out.println("response :" + responseCode);
		    
		    if(responseCode==200) {
		    	br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    }else {
		    	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		    }
		    String inputLine;
		    StringBuffer res = new StringBuffer();
		    while((inputLine=br.readLine())!=null) {
		    	res.append(inputLine);
		    }
		    br.close();
		    
		    if(responseCode==200) {
		    	inputLine = res.toString();
		    	System.out.println("inputLine : " + inputLine);
		    	
		    	//json 추출 과정
		    	JsonParser jp = new JsonParser();
		    	JsonElement element = jp.parse(inputLine);
		    	access_token = element.getAsJsonObject().get("access_token").getAsString();
		    	refresh_token= element.getAsJsonObject().get("refresh_token").getAsString();
		    	//json에서 access_toekn, refresh_token 추출 완료
		    	
		    	//session.setAttribute("naver_access_token", access_token);
		    	//session.setAttribute("naver_refresh_token", refresh_token);
		    	
		    	HashMap<String, String> profile = userdao.getNaverProfile(access_token);
		    	userVO userinfo = new userVO();
		    	userinfo.setId(profile.get("email"));
		    	userinfo.setName(profile.get("name"));
		    	userinfo.setNaverId(profile.get("id"));
		    	
//		    	System.out.println(profile.get("name"));
//		    	System.out.println(profile.get("birthday"));
//		    	System.out.println(profile.get("email"));
//		    	System.out.println(profile.get("id"));
		    	
		    	
				int check = userdao.kakaoLogin(userinfo.getId());
				
				System.out.println("check : " + check);
				
				if(check==1) {
					//user_info 데이터베이스에 가입되어 있는 경우
					mv.setViewName("/login/welcome");
					session.setAttribute("sessionID", userinfo.getId());
				}else {
					//user_info에 없으므로 가입 필요
					mv.addObject("userinfo", userinfo);
					//내용 전달을 위해서 설정
					mv.setViewName("/login/sign");
				}
		    }
		    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return mv;
	}
	
	@RequestMapping("kakaoLogin.certi")
	public ModelAndView kakaoLogin(userVO userinfo, HttpSession session) {
		mv = new ModelAndView();
		
		int check = userdao.kakaoLogin(userinfo.getId());
		
		System.out.println("check : " + check);
		
		if(check==1) {
			//user_info 데이터베이스에 가입되어 있는 경우
			mv.setViewName("/login/welcome");
			session.setAttribute("sessionID", userinfo.getId());
		}else {
			//user_info에 없으므로 가입 필요
			mv.addObject("userinfo", userinfo);
			//내용 전달을 위해서 설정
			mv.setViewName("/login/sign");
		}
		
		return mv; 
	}
	
	@RequestMapping("googleLogin.certi")
	public ModelAndView googleLogin(userVO userinfo, HttpSession session) {
		mv = new ModelAndView();
		
		int check = userdao.googleLogin(userinfo.getId());
		
		System.out.println("check : " + check);
		
		if(check==1) {
			//user_info 데이터베이스에 가입되어 있는 경우
			mv.setViewName("/login/welcome");
			session.setAttribute("sessionID", userinfo.getId());
		}else {
			//user_info에 없으므로 가입 필요
			mv.addObject("userinfo", userinfo);
			mv.setViewName("/login/sign");
		}
		
		
		return mv;
	}
	 
	@RequestMapping("sign.certi")
	public ModelAndView sign() {
		mv = new ModelAndView();
		
		List<Cer_CategoryVO> category = userdao.getCerti_Category();
		
		
		mv.addObject("category",category);
		mv.setViewName("/login/sign");
		
		return mv;
	}
	
	@RequestMapping("signpage.certi")
	public ModelAndView signPage() {
		mv = new ModelAndView();
		
		mv.setViewName("/login/signPage");
		
		return mv;
	}
	
}
