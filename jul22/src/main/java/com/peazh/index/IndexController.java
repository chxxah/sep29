package com.peazh.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//객체 생성
@Controller
public class IndexController {
	
	// 분류 흐름 : 사용자 요청이 들어오면 어디로 가게 할지 흐름을 제어
	
	// /r가 들어오면 index를 내보낼 거임
	@RequestMapping("/") // index
	public String index() {
		return "index"; // index.jsp
		// /WEB-INF/views/index.jsp 이라는 뜻
	}

}
