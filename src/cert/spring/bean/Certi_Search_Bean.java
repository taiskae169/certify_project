package cert.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import certi.Rjava.CertiInfoR;
import certify.vo.CertiInfoVO;

@Controller
@RequestMapping("/cerinfo/")
public class Certi_Search_Bean {
	
	ModelAndView mv = null;
	
	
	@RequestMapping("search.certi")
	public ModelAndView search(String certify) {
		mv = new ModelAndView();
		
		mv.setViewName("/search/search");
		return mv;
	}
	
	@RequestMapping("searchtest.certi")
	public ModelAndView searchtest(String certify) {
		mv = new ModelAndView();
		System.out.println("검색 창 시작");
		certify = "가구제작기능사";
		
		CertiInfoR usrR = new CertiInfoR();
		
		usrR.updateCertiInfo(1);
		
		
		mv.setViewName("/search/info");
		return mv;
	}

}
