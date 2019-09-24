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
	}
	
	@RequestMapping("searchtest.certi")
	public ModelAndView searchtest(String certify) {
		mv = new ModelAndView();
		System.out.println("검색 창 시작");
		certify = "가구제작기능사";
		
//		CertiInfoR usrR = new CertiInfoR();
//		certidao.deleteCertiCategory(0);
//		List<CertiInfoVO> certiList = usrR.updateCertiInfo(1);
//		for(CertiInfoVO vo : certiList) {
//			System.out.println(vo.getCerName());
//			certidao.updateCertiCategory0(vo);
//		}
		
		
		mv.setViewName("/search/info");
		return mv;
	}

}
