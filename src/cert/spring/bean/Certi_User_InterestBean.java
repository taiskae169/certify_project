package cert.spring.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import certify.vo.CertifyVO;
import user.vo.*;

@Repository
@Controller
@RequestMapping("/")

public class Certi_User_InterestBean {
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	ModelAndView mv =null;
	
	@RequestMapping("InterestMain.certi")
	public String BoardList(HttpSession session,Model model) {
		String id = (String)session.getAttribute("sessionID");
		
		List<userJoinInfoInterCertiVO> ujiiclist = null;
		
		ujiiclist = (List)sql.selectList("user.getUserInterCerti",id);
		model.addAttribute("ujiiclist", ujiiclist);
		
		return "certi/interest/InterestMain";
	}
	
	@RequestMapping("InterestSearchForm.certi")
	public String InterRestSearchForm(HttpSession session) {
		String id = (String)session.getAttribute("sessionID");
		
		
		
		return "certi/interest/InterestSearchForm";
	}
	
	@RequestMapping("InterestSearchPro.certi")
	public String InterRestSearchPro(String search,Model model) {
	
		String searchname = search;
		
		List<CertifyVO> certilist = null;
		
		certilist = (List)sql.selectList("certi.getCertifySearch", searchname);
		model.addAttribute("certilist", certilist);
		
		return "certi/interest/InterestSearchPro";
	}
	
	@RequestMapping("InterestSearchEnd.certi")
	public String InterRestSearchEnd(String certi,HttpSession session) {
		System.out.println(certi);
		int certiname = Integer.parseInt(certi);
		String id = (String)session.getAttribute("sessionID");
		
		userInterCertiVO uicvo = new userInterCertiVO();
		uicvo.setCer_name(certiname);
		uicvo.setId(id);
		uicvo.setAlarm(0);
		System.out.println(uicvo.getAlarm());
		System.out.println(uicvo.getId());
		System.out.println(uicvo.getCer_name());
	
		sql.insert("user.insertInter", uicvo);
		
		return "certi/interest/InterestSearchEnd";
	}
	

}
