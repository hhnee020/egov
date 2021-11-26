package egov.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egov.service.CommentVO;
import egov.service.ReviewService;
import egov.service.ReviewVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class ReviewController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	EgovPropertyService propertiesService;
	
	@Resource(name="reviewService")
	ReviewService reviewService;
	
	@RequestMapping("reviewWrite.do")
	public String reviewWrite() {
		
		return "admin/reviewWrite";
	}
	
	@RequestMapping("reviewWriteSave.do")
	@ResponseBody
	public String reviewWriteSave( ReviewVO vo  ) throws Exception {
		
		String msg = "ok";
		String result = reviewService.insertReview(vo);
		if(result != null) msg = "save_fail";
		
		return msg;
	}

	@RequestMapping("reviewList.do")
	public String selectReviewList( ReviewVO vo, Model model ) throws Exception {
		
		// 현페이지번호
		int page_no  = vo.getPage_no();
		int start_no = vo.getStart_no(); 
		int end_no   = vo.getEnd_no();

		vo.setStart_no(start_no); // SQL 시작번호
		vo.setEnd_no(end_no);     // SQL 끝번호
		
		List<?> list = reviewService.selectReviewList(vo);
		int total = reviewService.selectReviewTotal(vo);
		vo.setTotal(total);
		int total_page = vo.getTotal_page();
		
		// (1)-13, (2)-3
		int row_no = vo.getRow_no(); 

		model.addAttribute("total_page",total_page); // 총 페이지 번호
		model.addAttribute("total",total);    // 총 데이터 값
		model.addAttribute("list",list);      // 페이지 출력 목록
		model.addAttribute("row_no",row_no);  // 출력 페이지 행 시작 번호
		
		return "admin/reviewList";
	}
	
	@RequestMapping("reviewDetail.do")
	public String selectReviewDetail( ReviewVO vo, Model model ) throws Exception {
		
		// 상세보기 서비스 실행
		vo = reviewService.selectReviewDetail(vo);
		
		// 관련 코맨트 서비스 실행
		List<?> comm_list = reviewService.selectCommList(vo);
		
		String cont = vo.getContent();
		cont = cont.replace("&gt;",">");
		cont = cont.replace("&lt;","<");
		vo.setContent(cont);
		
		model.addAttribute("vo",vo);
		model.addAttribute("comm_list",comm_list);
		
		return "admin/reviewDetail";
	}
	
	@RequestMapping("commentSave.do")
	@ResponseBody
	public String insertComment( CommentVO vo ) throws Exception {

		// success : null ;
		String result = reviewService.insertComment(vo);
		String msg = "ok";
		if(result != null) msg = "save_fail";
		
		return msg;
	}

}











