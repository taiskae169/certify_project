package cert.spring.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Repository
@Controller
@RequestMapping("/")

public class Certi_User_InterestBean {
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	ModelAndView mv =null;
	
	@RequestMapping("InterestMain.certi")
	public String BoardList(HttpSession session) {
		String id = (String)session.getAttribute("memId");
		
		return "certi/interest/InterestMain";
	}
	
	@RequestMapping("InterestSearch.certi")
	public String InterRestSearch(HttpSession session) {
		String id = (String)session.getAttribute("memId");
		
		
		
		return "certi/interest/InterestSearch";
	}

}
