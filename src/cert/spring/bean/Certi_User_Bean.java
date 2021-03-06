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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import certify.user.dao.UserMethod;
import certify.vo.Cer_CategoryVO;
import user.vo.userVO;



//유저관련 페이지를 처리하기 위한 컨트롤러

@Controller
@RequestMapping("/user/")
public class Certi_User_Bean {
	
	
// 유저 관련 페이지를 위한 bean파일
// 로그인/ 개인정보 수정과 같은 페이지를 위한 컨트롤러입니다.
	ModelAndView mv = null;
	
	@Autowired
	UserMethod userdao = null;
	
	@RequestMapping("loginPro.certi")
	public ModelAndView loginPro(String id, String pw,HttpSession session) {
		//로그인페이지
		mv = new ModelAndView();

		System.out.println(id);
		System.out.println(pw);
		
		int i = userdao.logincheck(id, pw);
		//아이디 체크를 해서 아이디와 비밀번호가 맞을 경우 1을 리턴한다.
		System.out.println("로그인 체크 : " + i);
		if(i==1) {
			System.out.println("로그인 중");
			session.setAttribute("sessionID", id);
		}
		
		
		mv.setViewName("/login/welcome");
		return mv;
	}//일반적 로그인 체크, 현재 DB테스트로 인하여  간단한 테스트만을 설정하였음, 이후 변경 필요
	
	@RequestMapping("naverLogin.certi")
	public ModelAndView naverLogin(HttpSession session, String code) {
		//네이버 로그인 페이지
		//REST 방식으로 처리하였음
		//System.out.println("네이버 로그인 시작");
		mv = new ModelAndView();
		
		String clientId="UfsHgM0aSD0KyYettfN3";
		String clientSecret="jsiBe65lAg";
		//2개 내용은 네이버 API에서 프로젝트 생성 후에 확인 가능함
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
		    //요청 URI 설정
		    String access_token = "";
		    String refresh_token = "";
		    
		    URL url = new URL(apiURL);
		    HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    //설정한 uri를 URL 클래스를 통해 실행
		    con.setRequestMethod("GET");
		    //받아오는 방식을 get 방식으로 설정
		    int responseCode=con.getResponseCode();
		    //responsecode를 받아와 200일 시 정상 동작, 그 외일 시 오류발생
		    BufferedReader br;
		    
		    
		    //System.out.println("response :" + responseCode);
		    
		    if(responseCode==200) {
		    	br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    }else {
		    	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		    }
		    String inputLine;// 리턴되는 값들이 들어갈 변수
		    StringBuffer res = new StringBuffer();
		    while((inputLine=br.readLine())!=null) {
		    	res.append(inputLine);
		    	//inputLine에 각 한줄씩 추가
		    }
		    br.close();
		    
		    if(responseCode==200) {
		    	inputLine = res.toString();
		    	
		    	//json 추출 과정
		    	JsonParser jp = new JsonParser();
		    	JsonElement element = jp.parse(inputLine);
		    	access_token = element.getAsJsonObject().get("access_token").getAsString();
		    	refresh_token= element.getAsJsonObject().get("refresh_token").getAsString();
		    	//json에서 access_toekn, refresh_token 추출 완료

		    	HashMap<String, String> profile = userdao.getNaverProfile(access_token);
		    	userVO userinfo = new userVO();
		    	userinfo.setId(profile.get("email"));
		    	userinfo.setName(profile.get("name"));
		    	userinfo.setNaverId(profile.get("id"));

		    	
		    	
				int check = userdao.naverLogin(userinfo.getNaverId());
				
				System.out.println("check : " + check);
				
				if(check==1) {
					//user_info 데이터베이스에 가입되어 있는 경우
					mv.setViewName("/login/welcome");
					session.setAttribute("sessionID", userinfo.getId());
				}else {
					//user_info에 없으므로 가입 필요
					List<Cer_CategoryVO> category = userdao.getCerti_Category();
					mv.addObject("category",category);
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
		
		int check = userdao.kakaoLogin(userinfo.getKakaoId());
		// 카카오로그인을 클릭하여 전송되는 값을 바탕으로 유저 데이터 테이블에서 가입여부를 확인 
		//System.out.println("check : " + check);
		
		if(check==1) {
			//user_info 데이터베이스에 가입되어 있는 경우
			mv.setViewName("/login/welcome");
			session.setAttribute("sessionID", userinfo.getId());
		}else {
			//user_info에 없으므로 가입 필요
			List<Cer_CategoryVO> category = userdao.getCerti_Category();
			mv.addObject("category",category);
			mv.addObject("userinfo", userinfo);
			//내용 전달을 위해서 설정
			mv.setViewName("/login/sign");
		}
		
		return mv; 
	}
	
	@RequestMapping("googleLogin.certi")
	public ModelAndView googleLogin(userVO userinfo, HttpSession session) {
		mv = new ModelAndView();
		
		int check = userdao.googleLogin(userinfo.getGoogleId());
		// 구글로그인을 클릭하여 전송되는 값을 바탕으로 유저 데이터 테이블에서 가입여부를 확인 
		//System.out.println("check : " + check);
		
		if(check==1) {
			//user_info 데이터베이스에 가입되어 있는 경우
			mv.setViewName("/login/welcome");
			session.setAttribute("sessionID", userinfo.getId());
		}else {
			//user_info에 없으므로 가입 필요
			List<Cer_CategoryVO> category = userdao.getCerti_Category();
			mv.addObject("userinfo", userinfo);
			mv.addObject("category",category);
			mv.setViewName("/login/sign");
		}
		
		
		return mv;
	}
	 
	@RequestMapping("sign.certi")
	public ModelAndView sign(@ModelAttribute userVO vo) {
		//가입 페이지를 위한 컨트롤러
		mv = new ModelAndView();
		
		//System.out.println(vo.getKakaoId());
		
		
		List<Cer_CategoryVO> category = userdao.getCerti_Category();
		//가입페이지의 관심목록을 추가 하기 위해 list를 받아온다.
		
		mv.addObject("category",category);
		//페이지에서 사용하기 위해 설정
		mv.setViewName("/login/sign");
		
		return mv;
	}
	
//	@RequestMapping("signpage.certi")
//	public ModelAndView signPage() {
//		mv = new ModelAndView();
//		
//		mv.setViewName("/login/signPage");
//		
//		return mv;
//	}
	
	@RequestMapping(value="idcheck.certi")
	public @ResponseBody String idcheck(String email) {
		String str = "BLOCK";
		if(!email.trim().equals("")) {
			str =userdao.idCheck(email);
		}
		
		//System.out.println(str);
		return str;
	}

	@RequestMapping(value="signup.certi", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ModelAndView signUp(@ModelAttribute userVO vo) {
		mv = new ModelAndView();

		userdao.signUp(vo);
		//입력받은 정보를 바탕으로 유저 정보 DB에 입력
		mv.setViewName("/login/signup");
		return mv;
	}//가입처리 페이지
	
	@RequestMapping("logout.certi")
	public ModelAndView logout(HttpSession session) {
		mv = new ModelAndView();
		
		session.invalidate();
		//로그아웃, 세션을 삭제한다.
		mv.setViewName("/login/logout");
		return mv;
	}//로그아웃
	
	@RequestMapping("lookUp.certi")
	public ModelAndView lookUp() {
		mv = new ModelAndView();
		
		mv.setViewName("/login/lookUp");
		return mv;
	}//아이디 찾기, 이름과 날짜르 받는 페이지
	
	@RequestMapping(value="selectID.certi", method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ModelAndView selectID(String name, String birth) {
		mv = new ModelAndView();
		//System.out.println("name : " + name);
		//System.out.println("birth : " + birth);
		
		List<String> IDList = userdao.lookupID(name, birth);
		
		mv.addObject("IDList",IDList);
		//System.out.println(IDList.get(0));
		
		mv.setViewName("/login/selectID");
		//이름과 생년월일을 받아서 아이디를 가지고오고 해당 아이디 리스트를 만들어 전송합니다.
		return mv;
	}//아이디 찾기 - 아이디 선택화면
	
	@RequestMapping(value="selectIDPro.certi", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ModelAndView selectIDPro(String id) {
		mv = new ModelAndView();
		System.out.println("selectID 시작");
		String tmp = userdao.setTmpPw(id);
		userVO vo = new userVO();
		
		vo.setId(id);
		vo.setPw(tmp);
		
		try {
			userdao.sendPW(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		mv.setViewName("/login/selectIDPro");
		return mv;
	}//아이디ㅣ 비밀번호 생성
	
	
	
	
	
	
	
	
}
