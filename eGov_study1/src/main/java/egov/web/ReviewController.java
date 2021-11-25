package egov.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewController {

	@RequestMapping("reviewWrite.do")
	public String reviewWrite() {
		
		return "admin/reviewWrite";
	}
	
}
