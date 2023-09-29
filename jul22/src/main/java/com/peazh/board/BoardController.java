package com.peazh.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peazh.util.Util;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;  
	
	@Autowired
	private Util util;
	
	@GetMapping("/board")
	public String board(Model model) {
		// 서비스에서 값 가져오기 (가져오려면 연동해야 함)
		model.addAttribute("list", boardService.boardList());

		return "board";
	}

	// he
	// 파라미터로 들어오는 값 잡기
	@GetMapping("/detail") // model은 jsp에 값을 붙이기 위해서 넣었습니다.
	public String detail(HttpServletRequest request, Model model) {
		// String bno = request.getParameter("bno");
		int bno = util.str2Int(request.getParameter("bno"));
		
		
		BoardDTO dto = boardService.detail(bno);
		model.addAttribute("dto", dto);

		return "detail";
	}

	@GetMapping("/write")
	public String write() {
		return "write";
	}

	@PostMapping("/write")
	public String write(HttpServletRequest request) {
		
		// 사용자가 입력한 데이터 변수에 담기
		BoardDTO dto = new BoardDTO();
		dto.setBtitle(request.getParameter("title"));
		dto.setBcontent(request.getParameter("content"));
		dto.setBwrite("햇살");// 임시로 적은 것. 로그인 추가되면 변경 예정~

		// Service -> DAO -> mybatis -> DB로 보내서 저장하기
		boardService.write(dto);

		return "redirect:board";// 다시 컨트롤러 지나가기 GET방식으로 갑니다.
	}
	
	// 삭제할 때
	@GetMapping("/delete") 
	// RequestParam == HttpServletRequest의 getParameter();
	public String delete(@RequestParam(value = "bno", required = true, defaultValue = "0") int bno) {
		
		// 내 글만 삭제하기 위해서 dto 만들기
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		
		// 로그인을 하면 사용자의 정보를 같이 보내기
		boardService.delete(dto);
		
		
		return "redirect:board";// 삭제를 완료한 후에 다시 보드로 가기
	}
	
	// 수정하는 화면 만들기
	@GetMapping("/edit")
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("edit");// edit.jsp로 갈거야
		
		// 데이터 베이스에 bno를 보내고 dto를 얻어오기
		BoardDTO dto = boardService.detail(util.str2Int(request.getParameter("bno")));
		
		// mv에 보내기
		mv.addObject("dto", dto);
		
		return mv;
	}
	
	@PostMapping("/edit")
	public String edit(BoardDTO dto) {
		boardService.edit(dto);
		
		return "redirect:detail?bno="+dto.getBno();// 보드로 이동하게 해주세요
	}
	
	

}
