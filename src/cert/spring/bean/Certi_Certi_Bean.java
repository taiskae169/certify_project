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
		String id = (String)session.getAttribute("sessionID");
		int type = Integer.parseInt(request.getParameter("type"));
		mv.addObject("type",type);
		String choose_type = null;
		if(type==1) {
			choose_type="(0,1,2,3,4)";
		}else {
			choose_type="(5)";
		}
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		int count = certidao.getCount_gukki();
		/*
		 * 오전에 작업해야 할 내용 : 
		 * count를 국가기술별, 국가전문별로 나누고, 검색어가 있을 경우와 없을 경우를
		 * 구분하여 count를 받을 수 있도록 해야함
		 * 검색어 있는 경우, 없는 경우는 sql에서 <if>로 작업해보자.
		 * 자격증 리스트가 View에서 전체 출력되고 있으므로, 이를 index를 잘 해야함
		 * 
		 */
		
		
		if(pageNum==null) {
			pageNum="1";
		}
		
		int startRow = 0;
		int currentPage = Integer.parseInt(pageNum);
		int number = count-(currentPage - 1) * pageSize + 1;
		startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		HashMap<Object, Object> parameters = new HashMap<Object, Object>();
		parameters.put("start", startRow);
	    parameters.put("end", endRow);
	    parameters.put("type", choose_type);
	    
	    List<CertifyVO> certiList = null;
	    if(request.getParameter("cer_name")==null) {
	    	
	    	certiList = certidao.getCertify_Board(parameters);
	    }else {
	    	parameters.put("cer_name", request.getParameter("cer_name"));
	    	certiList = certidao.getCertify_Board_Spec(parameters);
	    }
	    mv.addObject("certiList",certiList);
	    mv.addObject("end", endRow);
	    mv.addObject("start", startRow);
	    mv.addObject("count", count);
		
		int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1 );
		int startPage = (int)(currentPage/10)*10+1;
			
		int endPage = pageCount;
			
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.setViewName("/certi/selfCheck/certi_sc_session1");
		return mv;
	}

}
