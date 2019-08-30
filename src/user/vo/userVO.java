package user.vo;

import java.sql.Timestamp;
import java.util.Date;

/*
 * 회원 정보 VO입니다
 */

public class userVO {
	public String id;
	public String pw;
	public String name;
	public Date birth;
	public String profile;
	public Timestamp reg;
	public int wana;
	public int qual;
	
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
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
	
}
