package certify.user.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import certify.mail.Email;
import certify.vo.Cer_CategoryVO;
import user.vo.userCareerVO;
import user.vo.userEduVO;
import user.vo.userVO;

public class UserMethod {

	
	@Autowired
	private SqlSessionTemplate sql =null;
	
	@Autowired
	private Email email;
	
	public int logincheck(String id, String pw) {
		int result =0;
		Map<String, String> idpw = new HashMap<String, String>();
		idpw.put("id", id);
		idpw.put("pw", pw);
		System.out.println("DB 테스트");
		result = sql.selectOne("user.logincheck", idpw);
		
		
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
		System.out.println("googleid : " + vo.getGoogleId());
		System.out.println("naverid : " + vo.getNaverId());
		System.out.println("kakaoid : " + vo.getKakaoId());
		sql.insert("user.sign", vo);
		System.out.println("가입 성공");
		
	}//회원가입을 위한 메소드
	
	public String getName(String id) {
		String name = sql.selectOne("user.getName",id);
		
		System.out.println(name);
		
		return name;
	}
	
	// 회원 학력사항 입력 메소드
	public void insertUserEdu(userEduVO uevo) {
		System.out.println("회원정보-학력을 저장합니다.");
		sql.insert("user.insertEdu", uevo);
	}
	
	// 회원 경력사항 입력 메소드
	public void insertUserCareer(userCareerVO ucvo) {
		System.out.println("회원정보-경력을 저장합니다.");
		sql.insert("user.insertCareer", ucvo);
	}
	
	//아이디 찾기를 위한 메소드
	public List<String> lookupID(String name, String birth) {
		List<String> list = null;
		userVO vo = new userVO();
		vo.setName(name);
		vo.setBirth(birth);
		
		list = sql.selectList("user.lookUpID",vo);
		
		return list;
	}
	
	//신규 비밀번호 생성 및 아이디 변경
	public String setTmpPw(String email) {
		String tmp = UUID.randomUUID().toString();
		tmp = tmp.split("-")[0];
		userVO vo = new userVO();
		vo.setId(email);
		vo.setPw(tmp);
		//System.out.println("tmp : " + tmp);
		sql.update("user.updatePW", vo);
		
		System.out.println("임시 비밀번호 등록 완료");
		
		return tmp;
	}
	
	//임시 비밀번호를 전송
	public void sendPW(userVO vo) throws Exception{
		email.setContent("새로운 비밀번호는 " + vo.getPw()+"입니다.");
		email.setReceiver(vo.getId());
		email.setSubject("자격루 임시 비밀번호입니다.");
	}
	
}
