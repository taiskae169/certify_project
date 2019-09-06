package cert.spring.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import certify.user.dao.UserMethod;

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
		mv = new ModelAndView();
		
		String clientId="UfsHgM0aSD0KyYettfN3";
		String clientSecret="siBe65lAg";
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
		    	
		    	session.setAttribute("naver_access_token", access_token);
		    	session.setAttribute("naver_refresh_token", refresh_token);
		    	
		    	//access토큰 추출 이후에 회원정보 추출이 필요
		    }
		    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return mv;
	}
	
}
