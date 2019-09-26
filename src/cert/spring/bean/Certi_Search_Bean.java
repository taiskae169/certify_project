package cert.spring.bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import test.readCSV.test.ReadCSVatUniv;

@Controller
@RequestMapping("/cerinfo/")
public class Certi_Search_Bean {
	
	ModelAndView mv = null;
	
	@Autowired
	CertifyMethod certidao =null;
	
	
	@RequestMapping("search.certi")
	public ModelAndView search(String certify, HttpSession session, HttpServletRequest request) 
			throws IOException {
		mv = new ModelAndView();
		
		String csvGisaPath = request.getSession().getServletContext().getRealPath("/csvSource/gisa.csv");
		String filepath = new File(csvGisaPath).getAbsolutePath();
		ReadCSVatUniv rcu = new ReadCSVatUniv();
		HashMap<String, Map<String, String>> gisaCerti = rcu.certiCSVtoMap(filepath);
		
//		if(certify!=null) {
//			List<CertifyVO> certiList = certidao.getCertiSpecList(certify);
//			for(CertifyVO cv :certiList) {
//				System.out.println(gisaCerti.get(cv.getName()));
//			}
//		}
		
		List<Cer_CategoryVO> list = certidao.getCerti_Category();
		mv.addObject("list", list);
		mv.setViewName("/search/search");
		//String sessionID = (String)session.getAttribute("sessionID");
		//mv.addObject("sessionID",sessionID);
		
		
		return mv;
	}//search 종료
	
	

}
