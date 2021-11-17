package egov.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egov.service.NBoardService;
import egov.service.NBoardVO;

@Controller
public class NBoardController {

	@Resource(name="nboardService")
	NBoardService nboardService;

	
	@RequestMapping("/admin_nboardList.do")
	public String admin_selectNBoardList( NBoardVO vo , Model model) throws Exception {
		
		
		
		//페이지 갯수;	
		int page_no = vo.getPage_no();
		
		
		// 출력범위 - 시작번호, 끝번호 설정
		int s_no = (page_no -1) *10  +1;
		int e_no = s_no +(10-1);
		
		vo.setS_no(s_no);
		vo.setE_no(e_no);
		
		vo.setUnq3("0"); //  값을 넣어주어 널처리가 안되게하기위함
		
		
		List<?> list =nboardService.selectNBoardList(vo);
		
		
		//데이터 총 수;
		int total = nboardService.selectNBoardTotal(vo);
		
		//총 페이지 수;
		int total_page =(int) Math.ceil((double)total/10);
		
		int rownum = total - (page_no-1)*10;
		

	  
		    model.addAttribute("list",list);
		    model.addAttribute("total",total);
		    model.addAttribute("total_page",total_page);
		    model.addAttribute("rownum",rownum);
		   
		
				
		return "admin/nboardList";
	}

	@RequestMapping("/admin_nboardWrite.do")
	public String admin_nboardWrite() {
		
		return "admin/nboardWrite";
	}
	
	@RequestMapping("/admin_nboardWriteSave.do")
	@ResponseBody
	public String  admin_insertNBoard( NBoardVO vo ) throws Exception {
				
		String result = nboardService.insertNBoard(vo);
		String message = "ok";
		
		if( result != null ) {
			message = "error";
		}
		return message;
	}
	
	@RequestMapping("/admin_nboardModify.do")
	public String admin_nboardModify( NBoardVO vo ,Model model )  throws Exception {
		
		int unq = Integer.parseInt(vo.getUnq());

		NBoardVO vo1 = nboardService.selectNBoardDetail(unq);
		nboardService.updateNBoardHits(vo);

		String unq1 = "";
		String unq2 = "";
		String data = "";
		String[] array;
		int len = 0;
		
		Map<String,String> map = new HashMap<String,String>();
		
		vo.setUnq1(unq+"");
		vo.setUnq2(null);
		List<?> list = nboardService.selectNBoardList(vo);
		len = list.size();
		if(len > 0) {
			data = (Map)list.get(len-1) + "";
			array = data.split(", ");
			unq1 = array[1].split("=")[1];
		}
		
		vo.setUnq1(null);
		vo.setUnq2(unq+"");
		list = nboardService.selectNBoardList(vo);
		len = list.size();
		if(len > 0) {
			data = (Map)list.get(0) + "";
			array = data.split(", ");
			unq2 = array[1].split("=")[1];
		}
		
		String unq_before = unq2;
		String unq_next = unq1;
		
		vo1.setUnq(""+unq);
		model.addAttribute("vo",vo1);
		model.addAttribute("before",unq_before);
		model.addAttribute("next",unq_next);
		
		return "admin/nboardModify";
	
		
	}
	@RequestMapping("/admin_nboardModifySave.do")
	@ResponseBody
	public String admin_nboardModifySave( NBoardVO vo ) throws Exception {
		
		
		int result = nboardService.updateNBoard(vo);
		String messge="ok";
		if(result != 1) messge="err";
		
		return messge;
	}
	
	@RequestMapping("/admin_nboardDelete.do")
	@ResponseBody
	public String admin_nboardDelete(  NBoardVO vo )  throws Exception {
		
		
		int result = nboardService.deleteNBorad(vo);
		
		String messge="ok";
		if(result != 1) messge="err";
		
		return messge;
		
		
	}
	
	
	@RequestMapping("/admin_nboardAllDelete.do")
	@ResponseBody
	public String admin_nboardAllDelete( String values ) throws Exception {
		
		// 11,8,5,
		// delete from nboard where unq='11' or unq='8' or unq='5'; 
		// delete from nboard where unq in(11,8,5);
		
		// 11,5,
		// delete from nboard where unq='11' or unq='5'; 
		// delete from nboard where unq in('11','5');
		
		// delete from nboard where unq='11';
		// delete from nboard where unq='5';
		
		// 11,8,5,  -->  11,8,5
		values = values.substring(0,values.length()-1);
		
		int result = nboardService.deleteNBoardAll(values);
		System.out.println("result : " + result);
		
		String message = "ok";
		if( result == 0 ) message = "fail";
		return message;
	}
	
}








