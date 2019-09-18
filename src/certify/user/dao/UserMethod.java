package certify.user.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import certify.vo.Cer_CategoryVO;
import user.vo.userVO;

public class UserMethod {

	
	@Autowired
	private SqlSessionTemplate sql =null;
	
	public int logincheck(String id, String pw) {
		int result =0;
		Map<String, String> idpw = new HashMap<String, String>();
		idpw.put("id", id);
		idpw.put("pw", pw);
		System.out.println("DB 테스트");
		int test = sql.selectOne("user.logincheck", idpw);
		System.out.println(test);
		
		
		return result;
		
	}
	//아이디/비밀번호를 체크하는 메소드 -> 아이디 불일치, 비밀번호 불일치를 구분하기 위해 수정필요
	
	
	public int naverLogin(String id) {
		int result=sql.selectOne("user.naverLogin",id);
		
		return result;
	}
	//네이버 로그인 확인
	//1일시 회원가입이 되어있는 사람
	public int kakaoLogin(String id) {
		int result=sql.selectOne("user.kakaoLogin",id);
		
		return result;
	}
	//카카오회원로그인 확인
	//1일시 회원가입이 되어있는 사람
	public int googleLogin(String id) {
		int result=sql.selectOne("user.googleLogin",id);
		
		return result;
	}
	//구글 로그인 확인
	//1일시 회원가입이 되어있는 사람
	
	
	public HashMap<String, String> getNaverProfile(String access_token){
		HashMap<String , String> profile = new HashMap<String,String>();
		
		System.out.println("프로필 가지져오기 시작");
		
		String header = "Bearer "+access_token;
		try {
			String apiURL="https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine=br.readLine())!=null) {
				response.append(inputLine);
			}
			br.close();
			
			
			JsonParser jp = new JsonParser();
			JsonElement element = jp.parse(response.toString());
			if(element.getAsJsonObject().get("message").getAsString().equals("success")) {
				JsonObject tmp = (JsonObject)jp.parse(response.toString());
				JsonObject info = (JsonObject)tmp.get("response");
				
				profile.put("email",info.get("email").getAsString());
				profile.put("name", info.get("name").getAsString());
				profile.put("birthday", info.get("birthday").getAsString());
				profile.put("gender", info.get("gender").getAsString());
				profile.put("id", info.get("id").getAsString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("프로필가져오기 끝");
		
		return profile;
	}
	
	public List<Cer_CategoryVO> getCerti_Category(){
		List<Cer_CategoryVO> category = sql.selectList("user.cer_category");
		
//		for(Cer_CategoryVO i :category) {
//			System.out.println(i.getName());
//		}

		return category;
	}//카테고리 리스트 가져오는 것

	public String idCheck(String id) {
		String str = "";
		int check = sql.selectOne("user.idCheck",id);
//		System.out.println(id);
//		System.out.println(check);
		
		if(check==0) {
			str="YES";
		}else {
			str="NO";
		}
		
		return str;
	}// id 중복확인을 위한 메소드
	
	public void signUp(userVO vo) {
		System.out.println("가입 시작");
		System.out.println("birth : " + vo.getBirth());
		System.out.println("id : " + vo.getId());
		System.out.println("pw :" + vo.getPw());
		//sql.insert("user.sign", vo);
		System.out.println("가입 성공");
		
	}
}
