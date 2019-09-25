package user.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import certify.user.dao.CertifyMethod;
import certify.vo.Cer_CategoryVO;

public class userCareerVO {
	// 회원의 경력정보를 리턴받는 VO입니다.
	
	@Autowired
	CertifyMethod cfm = null;
	
	private int num;
	private String id;
	private String company;
	private int comp_cate;
	private Date com_ent_date;
	private Date com_gra_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		// seq(USER_CAREER_SEQ)
		// not null
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		// id = session.getAttribute("sessionID");
		// not null
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		if(company==null) this.company = "default";
		else this.company = company;
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
	public void setCom_ent_date(Date com_ent_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String save = sdf.format(com_ent_date);
		this.com_ent_date = sdf.parse(save);
	}
	public Date getCom_gra_date() {
		return com_gra_date;
	}
	public void setCom_gra_date(Date com_gra_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String save = sdf.format(com_gra_date);
		this.com_gra_date = sdf.parse(save);
	}
	
}
