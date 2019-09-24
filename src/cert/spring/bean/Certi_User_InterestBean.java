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

import certify.vo.CertificationVO;
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
	
	@RequestMapping("InterestTypeForm.certi")
	public String InterRestTypeForm(Model model) {
		
		
		for(int i = 0; i < 8; i++){
			switch(i) {
			case 0:
				List<CertificationVO> certilist0 = null;
				certilist0 = (List)sql.selectList("certi.CertifiType",0);
				model.addAttribute("certilist0",certilist0);
			case 1:
				List<CertificationVO> certilist1 = null;
				certilist1 = (List)sql.selectList("certi.CertifiType",1);
				model.addAttribute("certilist1",certilist1);
			case 2:
				List<CertificationVO> certilist2 = null;
				certilist2 = (List)sql.selectList("certi.CertifiType",2);
				model.addAttribute("certilist2",certilist2);
			case 3:
				List<CertificationVO> certilist3 = null;
				certilist3 = (List)sql.selectList("certi.CertifiType",3);
				model.addAttribute("certilist3",certilist3);
			case 4:
				List<CertificationVO> certilist4 = null;
				certilist4 = (List)sql.selectList("certi.CertifiType",4);
				model.addAttribute("certilist4",certilist4);
			case 5:
				List<CertificationVO> certilist5 = null;
				certilist5 = (List)sql.selectList("certi.CertifiType",5);
				model.addAttribute("certilist5",certilist5);
			case 6:
				List<CertificationVO> certilist6 = null;
				certilist6 = (List)sql.selectList("certi.CertifiType",6);
				model.addAttribute("certilist6",certilist6);
			case 7:
				List<CertificationVO> certilist7 = null;
				certilist7 = (List)sql.selectList("certi.CertifiType",7);
				model.addAttribute("certilist7",certilist7);
			}
		}
		
		return "certi/interest/InterestTypeForm";
	}
	@RequestMapping("InterestTypePro.certi")
	public String InterRestTypePro(String cre_name,Model model,HttpSession session) {
	
		
		
		System.out.println(cre_name);
		int certiname = Integer.parseInt(cre_name);
		String id = (String)session.getAttribute("sessionID");
		
		userInterCertiVO uicvo = new userInterCertiVO();
		uicvo.setCer_name(certiname);
		uicvo.setId(id);
		uicvo.setAlarm(0);
		System.out.println(uicvo.getAlarm());
		System.out.println(uicvo.getId());
		System.out.println(uicvo.getCer_name());
	
		sql.insert("user.insertInter", uicvo);
		
		return "certi/interest/InterestTypePro";
	}
	

}
