package user.vo;

import java.util.Date;

public class userCertiVO {
	// 유저 보유 자격증 정보
	public int num;
	public String id;
	public int cate;
	public int type;
	public int cer_name;
	public Date cer_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCate() {
		return cate;
	}
	public void setCate(int cate) {
		this.cate = cate;
	}
	public int getCer_name() {
		return cer_name;
	}
	public void setCer_name(int cer_name) {
		this.cer_name = cer_name;
	}
	public Date getCer_date() {
		return cer_date;
	}
	public void setCer_date(Date cer_date) {
		this.cer_date = cer_date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
