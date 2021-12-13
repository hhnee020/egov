package egov.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
	public String admin_selectNBoardList( NBoardVO vo, Model model ) throws Exception {

		// 현재 출력할 페이지 번호
		int page_no = vo.getPage_no();

		// 데이터 출력 범위 설정 (시작 번호) ; 1page->1, 2page->11, 3page->21
		int s_no = ((page_no - 1) * 10) + 1;
		int e_no = s_no + (10-1);
		
		vo.setS_no(s_no);
		vo.setE_no(e_no);

		vo.setUnq3("0");
		List<?> list = nboardService.selectNBoardList(vo);
		int total    = nboardService.selectNBoardTotal(vo);
		
		// 총 페이지 수
		int total_page = (int) Math.ceil( (double)total/10 );
		
		// 행번호
		int rownum = total - (page_no-1)*10;

		//  [ { } { } { }   ]
		List<Map> list1 = new ArrayList<Map>();
		Map map1 = new HashMap();
		String udate = "";
		
		for(int i=0; i<list.size(); i++) {
			map1 = (Map) (list.get(i));        //{rn=1, unq=15, title=asasas, name=관리자, rdate=2021/11/11, udate=2021-11-11 09:39:04.0, hits=0}
			udate = map1.get("udate") + "";    // udate="2021-11-11 09:39:04.0";
			udate = udate.substring(0,10).replace("-","/"); // udate="2021/11/11
			map1.put("udate",udate);
			list1.add(map1);
		}
		
		//System.out.println("----------> " + list);
		
		model.addAttribute("s_field",vo.getS_field());
		model.addAttribute("s_text",vo.getS_text());

		model.addAttribute("list",list1);
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
	public String admin_nBoardModify( NBoardVO vo, Model model ) 
										throws Exception{

		int unq = Integer.parseInt(vo.getUnq());
		
		// 상세보기
		NBoardVO vo1 = nboardService.selectNBoardDetail(unq);
		
		// 조회수증가
		nboardService.updateNBoardHits(vo);

		// 사용변수 초기세팅
		String unq1 = "";  // 다음의 UNQ 값 얻음
		String unq2 = "";  // 이전의 UNQ 값 얻음
		String data = "";  // Map의 결과를 얻음   {rn=2, unq=19, title=주소 이전 안내, name=관리자, ... }
		String[] array;    // data.split(", ")  => unq=19
		int len = 0;       // 목록(list) 결과 개수
		
		List<?> list = null;
		
		/*
		 *  unq1 (다음 unq) 값을 얻기 위한 설정
		 */
		vo.setUnq1(unq+"");  // WHERE (unq > 17) 
		vo.setUnq2(null);
		list = nboardService.selectNBoardList(vo);
		
		len = list.size(); // 출력개수
		if(len > 0) {      // 다음 unq 값이 있는 경우
			data = (Map)list.get(len-1) + "";  // {rn=2, unq=19, title=주소 ...
			array = data.split(", ");          // 
			unq1 = array[1].split("=")[1];     // 다음 UNQ
		}
		
		/*
		 * unq2 (이전 unq) 값을 얻기 위한 설정
		 */
		vo.setUnq1(null);
		vo.setUnq2(unq+"");
		list = nboardService.selectNBoardList(vo);
		
		len = list.size();
		if(len > 0) {
			data = (Map)list.get(0) + "";   // {rn=4, unq=17, title=주소 ...
			array = data.split(", ");
			unq2 = array[1].split("=")[1];  // 이전 UNQ
		}

		String unq_before = unq2;
		String unq_next = unq1;
		
		vo1.setUnq(""+unq);
		model.addAttribute("vo",vo1);             // 상세보기 데이터
		model.addAttribute("before",unq_before);  // 이전 UNQ
		model.addAttribute("next",unq_next);      // 다음 UNQ
		model.addAttribute("s_field",vo.getS_field()); 
		model.addAttribute("s_text",vo.getS_text()); 
		
		return "admin/nboardModify";
	}
	
	@RequestMapping("/admin_nboardModifySave.do")
	@ResponseBody
	public String admin_nBoardModifySave( NBoardVO vo ) throws Exception {
		
		int result = nboardService.updateNBoard(vo);
		String message = "ok";
		
		if( result != 1 ) message = "fail";
		return message;
	}
	
	@RequestMapping("/admin_nboardDelete.do")
	@ResponseBody
	public String admin_nboardDelete( NBoardVO vo ) throws Exception {
		
		int result = nboardService.deleteNBoard(vo);
		String message = "ok";
		
		if( result != 1 ) message = "fail";
		return message;
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








