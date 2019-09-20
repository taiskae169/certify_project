package cert.spring.bean;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.vo.BoardVO;
import certify.user.dao.CertifyMethod;
import certify.user.dao.UserMethod;
import certify.vo.CertifyVO;
import user.vo.userCareerVO;
import user.vo.userCertiVO;
import user.vo.userEduVO;

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
	
	@RequestMapping("certi_sc_session1.certi")
	public ModelAndView certi_sc_session1(HttpSession session, HttpServletRequest request) {
		mv = new ModelAndView();
		
		int type = Integer.parseInt(request.getParameter("type"));
		String certi_name = request.getParameter("certi_name");
		mv.addObject("type",type);
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		System.out.println(certi_name);
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null) pageNum  ="1";
		int count=0;
		if(type==1) { 
			if(certi_name==null) {
				count = certidao.getCount_type1();
			}else {
				count = certidao.getCount_typeSpec1(certi_name);
			}
		}else {
			if(certi_name==null) {
				count = certidao.getCount_type1();
			}
			else {
				count = certidao.getCount_typeSpec2(certi_name);
			}
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		parameters.put("start", startRow);
	    parameters.put("end", endRow);
	    
	    List<CertifyVO> certiList = null;
	    if(count>0) {
		    if(certi_name==null) {
		    	if(type==1)certiList = certidao.getCertify_Board1(parameters);
		    	else certiList = certidao.getCertify_Board2(parameters);
		    }else {
		    	parameters.put("certi_name", certi_name); 
		    	if(type==1) certiList = certidao.getCertify_Board_Spec1(parameters);
		    	else certiList = certidao.getCertify_Board_Spec2(parameters);
		    }
		    int pageBlock = 10;
			int pageCount = count/pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage=(int)(currentPage/10)*10+1;
			int endPage = startPage + pageBlock-1;
			if(endPage>pageCount) endPage=pageCount;
			mv.addObject("pageCount",pageCount);
			mv.addObject("startPage",startPage);
			mv.addObject("endPage",endPage);
	    }
	    mv.addObject("type", type);
	    mv.addObject("certiList",certiList);
	    mv.addObject("end", endRow);
	    mv.addObject("start", startRow);
	    mv.addObject("count", count);
		mv.setViewName("/certi/selfCheck/certi_sc_session1");
		return mv;
	}

}
