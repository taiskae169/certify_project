package cert.spring.bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
		parameters.put("start", startRow);
	    parameters.put("end", endRow);
		
		   boardlist = (List)sql.selectList("board.getAriticle", parameters);
			model.addAttribute("boardlist", boardlist);
			model.addAttribute("end", endRow);
			model.addAttribute("start", startRow);
			model.addAttribute("count", count);
			
			int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1 );
			int startPage = (int)(currentPage/10)*10+1;
			
			int endPage = pageCount;
			
			
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
		return "/board/Boardlist";
	}
	
	@RequestMapping("BoardContent.certi")
	public String BoardContent(int num,Model model) {
		BoardVO vo = (BoardVO)sql.selectOne("board.content",num);
		model.addAttribute("board", vo);
		return "/board/Boardcontent";
	}
	
	@RequestMapping("BoardWriteForm.certi")
	public String BoardWriteForm() {
		return "/board/BoardWriteForm";
	}
	
	@RequestMapping(value="BoardWritePro.certi", method=RequestMethod.POST)
	public String loginBoardWritePro(BoardVO vo , 
				MultipartHttpServletRequest request,Model model,HttpSession session) throws IOException{
			MultipartFile mf = request.getFile("save");
			String imgs = request.getRealPath("imgs");
			
			String orgName = mf.getOriginalFilename(); 
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			
			int num = (Integer)sql.selectOne("bong.Filenum") + 1;
			String newName = "image"+num+ext; 
			File copyFile = new File(imgs+"//"+newName);
			mf.transferTo(copyFile);
			
			vo.setNewname(newName);
			vo.setOrgname(orgName);
			vo.setId((String)session.getAttribute("memId"));
			vo.setTitle(vo.getTitle());
			vo.setContent(vo.getContent());
			sql.insert("bong.fileInsert",vo);
			
			return "board/BoardWritePro";
		
		}
	
}
