package user.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import certify.user.dao.CertifyMethod;
import certify.vo.Cer_CategoryVO;
import certify.vo.CertifyVO;

public class userCertiVO {
	// 유저 보유 자격증 정보
	
	@Autowired
	CertifyMethod cfm = null;
	
	private int num;
	private String id;
	private int cate;
	private int type;
	private int cer_name;
	private Date cer_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		// not null : seq
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		// not null : session.getAttribute("sessionID");
		this.id = id;
	}
	public int getCate() {
		return cate;
	}
	public void setCate(int cate) {
		List<Cer_CategoryVO> cer_cate = cfm.getCerti_Category();
		for(Cer_CategoryVO cc : cer_cate) {
			if(cc.getCerti_num() == cate) {
				this.cate = cate; break;
			}else this.cate = 999; // 999 = 기타
		}
	}
	public int getCer_name() {
		return cer_name;
	}
	public void setCer_name(int cer_name) {
		List<CertifyVO> cvo = cfm.getAllCertify();
		for(CertifyVO cfv : cvo) {
			if(cfv.getNum() == cer_name) {
				this.cer_name = cer_name; break;
			}else this.cer_name = 999; // 999 = 기타
		}
	}
	public Date getCer_date() {
		return cer_date;
	}
	public void setCer_date(Date cer_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String save = sdf.format(cer_date);
		this.cer_date = sdf.parse(save);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
