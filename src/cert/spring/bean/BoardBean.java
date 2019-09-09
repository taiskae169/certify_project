package cert.spring.bean;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import board.vo.*;

@Repository
@Controller
@RequestMapping("/")
public class BoardBean {
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	ModelAndView mv =null;
	
	@RequestMapping("BoardList.certi")
	public String BoardList(HttpSession session,HttpServletRequest request,Model model) {
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		int count = (Integer)sql.selectOne("board.getCount");
		
		if(pageNum==null) {
			pageNum="1";
		}
		
		int startRow = 0;
		int currentPage = Integer.parseInt(pageNum);
		int number = count-(currentPage - 1) * pageSize + 1;
		startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		List<BoardVO> boardlist = null;
		
		HashMap<Object, Object> parameters = new HashMap<Object, Object>();
		
		boardlist = (List)sql.selectList("board.getArticle");
		model.addAttribute("boardlist",boardlist);
		model.addAttribute("end",endRow);
		model.addAttribute("start",startRow);
		model.addAttribute("count",count);
		
		int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1);
		int startPage = (int)(currentPage/10)*10+1;
		System.out.println(pageCount);
		int endPage = pageCount;
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
			
		return "/board/main";
	}
	
}
