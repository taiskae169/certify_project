package cert.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class Certi_Bean {

	ModelAndView mv =null;
	
	@RequestMapping("main.certi")
	public ModelAndView mainpage() {
		mv = new ModelAndView();
		

		mv.setViewName("/main/main");
		return mv;
	}
	
	
	
//	@RequestMapping("error.certi")
//	public ModelAndView errorpage() {
//		mv = new ModelAndView();
//		
//		System.out.println("errorpage");
//		mv.setViewName("error");
//		
//		return mv;
//	}
//	���������� �Ͻ� ����
	
}
