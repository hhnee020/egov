package egov.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egov.service.NBoardService;
import egov.service.NBoardVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class NBoardController {

	@Resource(name ="adminservice")
	private NBoardService nboardservice;
	
	@RequestMapping(value ="admin_nboardList.do")
	public String admin_selectNBoardList(NBoardVO vo,Model model) throws Exception{
		
		
		int totalcount = nboardservice.selectNBoardTotal(vo);
		int pageNo = vo.getPageNo();
		int sIndex = ((pageNo-1)*10);
		int eIndex = sIndex+9;
		vo.setsNo(totalcount-sIndex);
		vo.seteNo(totalcount-eIndex);
		int totalpage = (int) Math.ceil((double)totalcount/9);
		
//		int rownum = totalcount - (pageNo-1)*9;
		
		List<?> result = nboardservice.selectBoardList(vo);
		
		result.forEach( data ->{
			EgovMap map = (EgovMap) data;
			for(int i =0 ; i < map.size(); i++) {
				if(map.get(i).equals("udate")) {
					String str = map.getValue(i).toString();
					map.setValue(i, str.substring(0, 10).replaceAll("-", "/"));
				}
			}
		});
//		vo.setUnq3("0");
//		int pre =0;
//		int next = 0;
//		for(int i = 0; i < result.size(); i++) {
//			if(i == 0) {
//				EgovMap map = (EgovMap) result.get(i);
//				EgovMap nextmap = (EgovMap) result.get(i+1);
//			}
//			
//		}
		
		
		model.addAttribute("s_field", vo.getS_field());
		model.addAttribute("s_text", vo.getS_text());
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("total", totalcount);
		model.addAttribute("result", result);
		return "admin/nboardList";
		
	}
	
	@RequestMapping(value ="admin_nboardWrite.do")
	public String admin_nboardWrite() throws Exception {
		return "admin/nboardWrite";
	}
	
	@RequestMapping(value ="admin_nboardWriteSave.do")
	@ResponseBody
	public String admin_nboardWriteSave(NBoardVO vo) throws Exception {
		System.out.println(vo.getTitle());
		System.out.println(vo.getName());
		System.out.println(vo.getContent());
		
		
		
		String result = nboardservice.insertNboard(vo);
		String message ="ok";
		if(result != null) {
			message ="error";
		}
		return message;
	}
	
	@RequestMapping(value ="admin_nboardModify.do")
	public String admin_nboardModify(NBoardVO vo, Model model) throws Exception {
		NBoardVO result = nboardservice.selectNBoardDetail(vo);
		if(result != null) {

			model.addAttribute("s_field", vo.getS_field());
			model.addAttribute("s_text", vo.getS_text());
			model.addAttribute("vo",result);
			
		}
		return "admin/nboardModify";
	}
	
	@RequestMapping(value ="admin_nboardModifySave.do")
	@ResponseBody
	public String admin_nboardModifySave(NBoardVO vo) throws Exception {
		int result = nboardservice.updatenboard(vo);
		String message = "";
		if(result >= 1) message = "ok";
		else message ="error";
		
		return message;
	}

	@RequestMapping(value ="admin_nboardDelete.do")
	@ResponseBody
	public String admin_nboardDelete(int unq) throws Exception {
		String message ="";
		int result = nboardservice.deletenboard(unq);
		if(result >= 1 ) message ="ok";
		else message ="error";
		return message;
	}

	@RequestMapping(value ="admin_nboardAllDelete.do")
	@ResponseBody
	public String admin_nboardAllDelete(@RequestParam(value = "array_data[]", required = false) List<String> checklist ) throws Exception {

		
		HashMap<String,List<String>> map = new HashMap<String, List<String>>();
		map.put("idList", checklist);
		int result = nboardservice.deleteAllnboard(map);
		
		String message ="";
		
		if(result >= 1 ) message ="ok";
		else message ="error";
		return message;
	}
}
