package cert.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class Certi_Bean {

	ModelAndView mv =null;
	
	@RequestMapping("main")
	public ModelAndView main() {
		mv = new ModelAndView();
		
		System.out.println("start");

		mv.setViewName("/test");
		return mv;
	}
	
}
