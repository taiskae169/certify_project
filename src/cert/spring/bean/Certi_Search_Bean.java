package cert.spring.bean;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import certi.Rjava.CertiInfoR;
import certify.user.dao.CertifyMethod;
import certify.vo.Cer_CategoryVO;
import certify.vo.CertiInfoVO;
import certify.vo.CertifyVO;

@Controller
@RequestMapping("/cerinfo/")
public class Certi_Search_Bean {
	
	ModelAndView mv = null;
	
	@Autowired
	CertifyMethod certidao =null;
	
	
	@RequestMapping("search.certi")
	public ModelAndView search(String certify, HttpSession session) {
		mv = new ModelAndView();
		List<Cer_CategoryVO> list = certidao.getCerti_Category();
		mv.addObject("list", list);
		mv.setViewName("/search/search");
		//String sessionID = (String)session.getAttribute("sessionID");
		//mv.addObject("sessionID",sessionID);
		
		
		return mv;
	}//search 종료
	


}
