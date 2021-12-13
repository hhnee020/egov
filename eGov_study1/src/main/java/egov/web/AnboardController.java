package egov.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egov.service.AnboardService;
import egov.service.AnboardVO;
import egov.service.CommonVO;

@Controller
public class AnboardController {

	@Resource(name="anboardService")
	AnboardService anboardService;
	
	@RequestMapping("anboardWrite.do")
	public String anboardWrite() {
		
		return "admin/anboardWrite";
	}
	
	@RequestMapping("anboardWriteSave.do")
	@ResponseBody
	public String insertAnboard( AnboardVO vo ) throws Exception {
		
		String msg = "ok";
	
		// 저장 서비스 실행
		String result = anboardService.insertAnboard(vo);
		if( result != null ) msg = "save_fail"; 
		
		return msg;
		
	}
	
	@RequestMapping("anboardModifySave.do")
	@ResponseBody
	public String updateAnboard( AnboardVO vo ) throws Exception {
		
		String msg = "ok";
	
		// 암호 확인 서비스 실행
		int cnt_pass = anboardService.selectAnboardPass(vo);
		
		if( cnt_pass > 0 ) {  // 암호 정상 입력
			// 수정 서비스 실행
			int result = anboardService.updateAnboard(vo);
			if( result == 0 ) msg = "save_fail"; 
		
		} else {  // 암호 체크 실패
			msg = "pass_fail";
		}
		return msg;
		
	}
	
	
	
	@RequestMapping("anboardDelete.do")
	@ResponseBody
	public String deleteAnboard( AnboardVO vo ) throws Exception {
		
		String msg = "ok";
		int result = 0;
		
		// ok : 1 ; fail : 0
		int cnt_pass = anboardService.selectAnboardPass(vo);
		
		if( cnt_pass == 0 ) { // 암호 불일치
			msg = "pass_fail";
	
		} else {  // 암호 일치

			int cnt_lev = anboardService.selectAnboardLev(vo);
			if( cnt_lev > 0 ) { // 댓글 존재
				result = anboardService.updateAnboardDel(vo);
				
			} else {  // 댓글 비존재
				result = anboardService.deleteAnboard(vo);
			}
		}
		
		if(result == 0) msg = "save_fail";
		
		return msg;
	}
	

	
	@RequestMapping("anboardList.do")
	public String selectAnboardList(  CommonVO vo , Model model )  
														throws Exception {
		int page_no = vo.getPage_no();
		int page_unit = vo.getPage_unit();
		
		// 1p -> 1~10 , 2p -> 11~20 , 3p -> 21~30
		int s_no = (page_no-1)*page_unit + 1;
		int e_no = s_no + (page_unit-1);
		
		vo.setS_no(s_no);
		vo.setE_no(e_no);
		
		int total = anboardService.selectAnboardTotal(vo);
		int total_page =  (int) Math.ceil((double)total/10);  // ceil -> 3.0
		
		// 3 -> //
		int row_no = total - (page_no-1)*10;
		
		vo.setTotal(total);
		vo.setTotal_page(total_page);
		vo.setRow_no(row_no);
		
		// 목록 서비스 실행
		List<?> list = anboardService.selectAnboardList(vo);	
		model.addAttribute("list",list);
		model.addAttribute("vo",vo);
		
		return "admin/anboardList";
	}
	
	@RequestMapping("anboardDetail.do")
	public String selectAnboardDetail( AnboardVO vo , Model model ) throws Exception {

		// 조회수 증가 서비스 실행
		// update anboard set hits=hits+1 where unq=??;
		anboardService.updateAnboardHits(vo);
		
		
		// 상세 보기 서비스 실행
		vo = anboardService.selectAnboardDetail(vo);
		model.addAttribute("vo",vo);
		
		return "admin/anboardDetail";
	}
	
	@RequestMapping("anboardModify.do")
	public String selectAnboardModify( AnboardVO vo , Model model ) throws Exception {

		vo = anboardService.selectAnboardDetail(vo);
		model.addAttribute("vo",vo);
		
		return "admin/anboardModify";
	}
	

	@RequestMapping("replyWrite.do")
	public String replyWrite( AnboardVO vo , Model model ) throws Exception {
		
		model.addAttribute("vo",vo);
		
		return "admin/replyWrite";
	}
	
	@RequestMapping("replyWriteSave.do")
	@ResponseBody
	public String insertAnboardReply(  AnboardVO vo ) throws Exception {
	
		String msg = "ok";

		String result = anboardService.insertAnboardReply(vo);
		if( result != null ) msg = "save_fail";

		return msg;
		
	}
	
	
}







