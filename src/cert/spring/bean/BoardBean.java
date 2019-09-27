package cert.spring.bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mortbay.jetty.Request;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.vo.*;


@Repository
@Controller
@RequestMapping("/")
public class BoardBean {
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	ModelAndView mv =null;
	
	@RequestMapping("BoardList.certi")
	public String BoardList(HttpSession session,HttpServletRequest request,Model model,int catenum) {
		
		if(catenum==10) { //catenum이 10인 경우는 전체글보기
			int pageSize = 10;
			String pageNum = request.getParameter("pageNum");
			int count = (Integer)sql.selectOne("board.getCountALL");
			if(pageNum==null) {
				pageNum="1";
			}
			
			int startRow = 0;
			int currentPage = Integer.parseInt(pageNum);
			int number = count-(currentPage - 1) * pageSize + 1;
			startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			
			List<BoardVO> boardlist = null; //BoardVO 형태 리스트 생성
			
			HashMap<Object, Object> parameters = new HashMap<Object, Object>();
			parameters.put("start", startRow);
		    parameters.put("end", endRow);
			
			boardlist = (List)sql.selectList("board.getAriticleALL", parameters);
			model.addAttribute("boardlist", boardlist);
			model.addAttribute("end", endRow);
			model.addAttribute("start", startRow);
			model.addAttribute("count", count); //count 값을 조사하여 리스트에 글이 있음과 없을 표시하기위하여 값 전달
			
				int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1 );
				int startPage = (int)(currentPage/10)*10+1;
				
				int endPage = pageCount;
				
				List<BoardCateVO> catelist = null;
				catelist = (List)sql.selectList("board.getCateArticle");
				model.addAttribute("catelist",catelist);
				
				
				model.addAttribute("startPage", startPage);
				model.addAttribute("endPage", endPage);
		}
		else {// catenum이 10이 아닌경우 카테고리가 있는 경우
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		int count = (Integer)sql.selectOne("board.getCountCate",catenum);
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
	    parameters.put("cate", catenum);
		
		boardlist = (List)sql.selectList("board.getAriticleCate", parameters);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("end", endRow);
		model.addAttribute("start", startRow);
		model.addAttribute("count", count);
			
		
			
			int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1 );
			int startPage = (int)(currentPage/10)*10+1;
			
			int endPage = pageCount;
			
			List<BoardCateVO> catelist = null;
			catelist = (List)sql.selectList("board.getCateArticle");
			model.addAttribute("catelist",catelist);
			
			
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
		}
			
		return "/board/Boardlist";
	}
	
	@RequestMapping("BoardContent.certi")
	public String BoardContent(int num,HttpSession session,Model model) {
		
		BoardVO vo = (BoardVO)sql.selectOne("board.content",num);
		model.addAttribute("board", vo);
		int count = sql.selectOne("board.CommentCount", num);
		List<CommentVO> Clist = null;
		Clist = (List)sql.selectList("board.getComment", num);
		model.addAttribute("Clist", Clist);
		model.addAttribute("count", count);
		List<BoardCateVO> catelist = null;
		catelist = (List)sql.selectList("board.getCateArticle");
		model.addAttribute("catelist",catelist);
		
		return "/board/Boardcontent";
	}
	
	@RequestMapping("BoardWriteForm.certi")
	public String BoardWriteForm(HttpSession session,Model model) {
		List<BoardCateVO> catelist = null;
		catelist = (List)sql.selectList("board.getCateArticle");
		model.addAttribute("catelist",catelist);
		return "/board/BoardWriteForm";
	}
	
	@RequestMapping(value="BoardWritePro.certi", method=RequestMethod.POST)
	public String BoardWritePro(BoardVO vo ,
			MultipartHttpServletRequest request,Model model,HttpSession session) throws IOException {
			
			String newName = "";
			String orgName = "";
			
		if(request.getFile("save")!=null) {
			MultipartFile mf = request.getFile("save"); //save 이름으로 되어있는 file 받아오기
			String imgs = request.getRealPath("imgs"); // imgs 이미지 실제 주소 받아오기

			
			orgName = mf.getOriginalFilename(); //파일 이름
			String ext = orgName.substring(orgName.lastIndexOf('.')); // 확장자 가져오기
			
			
			int num = (Integer)sql.selectOne("board.Filenum") + 1; //파일 개수 가져오기
			newName = "image"+num+ext; //새로운 파일 이름 만들기
			File copyFile = new File(imgs+"//"+newName); 
			mf.transferTo(copyFile);
		}	
		else {
			newName = null;
			orgName = null;
		}
			
			vo.setNewname(newName);
			vo.setOrgname(orgName);
			vo.setId((String)session.getAttribute("sessionID"));
			vo.setTitle(vo.getTitle());
			vo.setCate(vo.getCate());
			vo.setContent(vo.getContent());
			vo.setReadcount(0);
			
			sql.insert("board.fileInsert",vo);
			
			return "board/BoardWritePro";
		
		}
	
	@RequestMapping("Download.certi")
	public ModelAndView download(String newname,HttpServletRequest request) {
		
		String path = request.getRealPath("imgs");
		File f = new File(path + "//" + newname);
	
		ModelAndView mv = new ModelAndView("downloadView","downloadFile",f);
		return mv;
	}
	
	@RequestMapping("BoardUpdateForm.certi")
	public String BoardUpdateForm(HttpSession session,Model model,int num) {
		
		List<BoardCateVO> catelist = null;
		catelist = (List)sql.selectList("board.getCateArticle");
		model.addAttribute("catelist",catelist);
		BoardVO vo = (BoardVO)sql.selectOne("board.UpdateForm",num);
		model.addAttribute("board", vo);
		return "/board/BoardUpdateForm";
	}
	
	@RequestMapping(value="BoardUpdatePro.certi", method=RequestMethod.POST)
	public String BoardUpdatePro(BoardVO vo , 
				MultipartHttpServletRequest request,Model model,HttpSession session) throws IOException{
		    
			String orgName = null;
		    String newName = null;
	
		    if(request.getFile("save") != null) {
			MultipartFile mf = request.getFile("save");
			String imgs = request.getRealPath("imgs");
			
			orgName = mf.getOriginalFilename(); 
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			
			int num = (Integer)sql.selectOne("board.Filenum") + 1;
			newName = "image"+num+ext; 
			File copyFile = new File(imgs+"//"+newName);
			mf.transferTo(copyFile);
			
			System.out.println(vo.getNum());
		    }
			

			vo.setNewname(newName);
			vo.setOrgname(orgName);
			vo.setId((String)session.getAttribute("sessionID"));
			vo.setTitle(vo.getTitle());
			vo.setCate(vo.getCate());
			vo.setContent(vo.getContent());
			vo.setNum(vo.getNum());
			
			sql.update("board.UpdateBoard",vo);
			
			return "board/BoardUpdatePro";
		
		}
	@RequestMapping("BoardDelete.certi")
	public String BoardDelete(int num) {
		sql.delete("board.DeleteBoard", num);
		return "/board/BoardDelete";
	}
	
	@RequestMapping("BoardCommentWrite.certi")
	public String BoardCommentWrite(CommentVO cvo,HttpSession session,int num,Model model) {
		cvo.setId((String)session.getAttribute("sessionID"));
		cvo.setTable_num(num);
		cvo.setContent(cvo.getContent());
		
		model.addAttribute("num", num);
		sql.insert("board.CommentWrite", cvo);
		return "/board/BoardCommentWrite";
	}
	
	@RequestMapping("BoardReContent.certi")
	public String BoardReComment(int b_num,int c_num,HttpSession session,Model model) {
		
		BoardVO vo = (BoardVO)sql.selectOne("board.content",b_num);
		model.addAttribute("board", vo);
	
		int count = sql.selectOne("board.CommentCount", b_num); //댓글 개수 가져오기
		
		List<CommentVO> Clist = null;
		Clist = (List)sql.selectList("board.getComment", b_num);
		model.addAttribute("Clist", Clist);
		model.addAttribute("count", count);
		model.addAttribute("comment_num", c_num);
		
		return "/board/Boardcontent";
	}
	
	@RequestMapping("BoardReCommentWrite.certi")
	public String BoardReCommentWrite(CommentVO cvo,HttpSession session,int b_num,int c_num,Model model) {
		cvo.setId((String)session.getAttribute("sessionID"));
		cvo.setTable_num(b_num);
		cvo.setContent(cvo.getContent());
		cvo.setNum(c_num);
		cvo.setStep(cvo.getStep()+1); //현재 스탭보다 개수를 하나 더 많게 하여 답글 구분하기
		
		
		model.addAttribute("num", b_num);
		sql.insert("board.ReCommentWrite", cvo);
		return "/board/BoardCommentWrite";
	}
	
	@RequestMapping("BoardCommentDelete.certi")
	public String BoardCommentDelete(HttpSession session,int b_num,int c_num,Model model) {
		model.addAttribute("num", b_num);
		sql.delete("board.CommentDelete", c_num);
		return "/board/BoardCommentDelete";
	}
	
	
	
}
