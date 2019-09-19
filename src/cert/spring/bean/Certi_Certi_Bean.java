package cert.spring.bean;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

@Repository
@Controller
@RequestMapping("/certi/")
public class Certi_Certi_Bean {
	ModelAndView mv =null;
	
	@Autowired
	UserMethod userdao = null;
	@Autowired
	CertifyMethod certidao = null;
	@Autowired
	userCareerVO ucvo = null;
	@Autowired
	userCertiVO uctvo = null;
	@Autowired
	userEduVO uevo = null;
	
	@RequestMapping(value="certi_sc_session1.certi")
	public ModelAndView certi_sc_session1(HttpSession session, String num, String cer_name) {
		String id = (String)session.getAttribute("sessionID");
		
		List<CertifyVO> certiList = userdao.getAllCertify();
		
		mv.addObject("certiList",certiList);
		mv.setViewName("/certi/selfCheck/certi_sc_session1");
		return mv;
	}

}
