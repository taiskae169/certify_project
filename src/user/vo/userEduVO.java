package user.vo;

import java.util.Date;

public class userEduVO {
	public String id;
	public int edu;
	public int major;
	public int state;
	public Date ent_date;
	public Date gra_date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEdu() {
		return edu;
	}
	public void setEdu(int edu) {
		this.edu = edu;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getEnt_date() {
		return ent_date;
	}
	public void setEnt_date(Date ent_date) {
		this.ent_date = ent_date;
	}
	public Date getGra_date() {
		return gra_date;
	}
	public void setGra_date(Date gra_date) {
		this.gra_date = gra_date;
	}
	
	
}
