package user.vo;

import java.util.Date;

public class userCareerVO {
	
	// 회원의 경력정보를 리턴받는 VO입니다.
	
	public int num;
	public String id;
	public String company;
	public int comp_cate;
	public Date com_ent_date;
	public Date com_gra_date;
	
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getComp_cate() {
		return comp_cate;
	}
	public void setComp_cate(int comp_cate) {
		this.comp_cate = comp_cate;
	}
	public Date getCom_ent_date() {
		return com_ent_date;
	}
	public void setCom_ent_date(Date com_ent_date) {
		this.com_ent_date = com_ent_date;
	}
	public Date getCom_gra_date() {
		return com_gra_date;
	}
	public void setCom_gra_date(Date com_gra_date) {
		this.com_gra_date = com_gra_date;
	}
	
}
