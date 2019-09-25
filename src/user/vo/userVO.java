package user.vo;

import java.sql.Timestamp;
import java.util.Date;

/*
 * 회원 정보 VO입니다
 */

public class userVO {
	public String id;		//아이디 - 이메일 형식
	public String pw;		//비밀번호
	public String name;		//이름
	public String birth;	//생년월일
	public String profile;	//프로필 사진
	public Timestamp reg;	//가입날자
	public int wana;		//관심 분야
	public int qual;		//응시가능 자격
	public String naverId;	//네이버 아이디
	public String kakaoId;	//카카오 아이디
	public String googleId; //구글 아이디
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public int getWana() {
		return wana;
	}
	public void setWana(int wana) {
		this.wana = wana;
	}
	public int getQual() {
		return qual;
	}
	public void setQual(int qual) {
		this.qual = qual;
	}
	public String getNaverId() {
		return naverId;
	}
	public void setNaverId(String naverId) {
		this.naverId = naverId;
	}
	public String getKakaoId() {
		return kakaoId;
	}
	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
}
