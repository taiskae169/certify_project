package user.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.Cer_CategoryVO;

public class userEduVO {
	
	@Autowired
	UserMethod userdao = null;
	@Autowired
	CertifyMethod cfm = null;
	
	private String id;
	private String edu_name;
	private String major_name;
	private int edu;
	private int major;
	private int state;
	private Date ent_date;
	private Date gra_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		// not null : seq
		this.id = id;
	}
	public String getEdu_name() {
		return edu_name;
	}
	public void setEdu_name(String edu_name) {
		if(edu_name.contains("학교") || edu_name.contains("대학")) this.edu_name = edu_name;
		else this.edu_name = "기타 - "+edu_name;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
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
		// not null : tag <select>
		this.state = state;
	}
	public Date getEnt_date() {
		return ent_date;
	}
	public void setEnt_date(Date ent_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String save = sdf.format(ent_date);
		this.ent_date = sdf.parse(save);
	}
	public Date getGra_date() {
		return gra_date;
	}
	public void setGra_date(Date gra_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String save = sdf.format(gra_date);
		this.gra_date = sdf.parse(save);
	}
	
	
}
