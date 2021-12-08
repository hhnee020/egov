package egov.web;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egov.service.EmpService;
import egov.service.EmpVO;
import egovframework.example.sample.service.EgovSampleService;

@Controller
public class EmpController {
	
	@Resource(name="empService")
	private EmpService empService;
	
	@Resource(name="sampleService")
	private EgovSampleService sampleService;

	@RequestMapping(value="empList.do")
	public String selectEmpList(EmpVO vo, Model model) throws Exception {
		
		/*
		 * pageNo = ((pageNo == null || pageNo == "0") ? "1" : pageNo); 
		 * int pagenumber =
		 * Integer.parseInt(pageNo);
		 */
		int page_no = vo.getPage_no();
		System.out.println(page_no);

		System.out.println("컨트롤러 접근");
		int totalCount = empService.selectEmpTotal();
		System.out.println("컨트롤러 : " + totalCount);
		
		int totalPage = (int) Math.ceil((double)totalCount/10);
		
		int s_index = (page_no-1)*10; //1이 들어오면 1-1 = 0 * 10 = 0 sql 데이터를 0번부터
									  //2가 들어오면 2-1 = 1 * 10 = 10 sql 데이터를 10번부터
		int e_index = (s_index+9);
		
		vo.setS_index(totalCount-s_index);
		vo.setE_index(totalCount-e_index);
		
		
		
	
		List<?> list = empService.selectEmpList(vo);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("list", list);
		
		return "emp/empList";
		
	}
	
	@RequestMapping(value = "empWrite.do")
	public String empWrite(Model model,EmpVO vo) throws Exception {
		
		int maxnumber = empService.maxcount();
		List<?> deptlist= sampleService.selectDeptList();
		List<?> joblist = empService.selectJobList();
		System.out.println("deptlist 클래스 "+deptlist.get(0).getClass());
		System.out.println("joblist 클래스"+joblist.get(0).getClass());
		
		model.addAttribute("joblist",joblist);
		model.addAttribute("deptlist", deptlist);
		model.addAttribute("maxnumber", maxnumber);
		return "emp/empWrite";
	}
	

	@RequestMapping(value = "empWriteSave.do")
	public String empWriteSave(EmpVO vo) throws Exception {

		String date;
		if(vo.getHiredate() == null || vo.getHiredate().equals("")) {
			Calendar cal = Calendar.getInstance();
			date = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE);
		} else {
			String datelist[] = vo.getHiredate().split("/");
			date = datelist[2]+"/"+datelist[0]+"/"+datelist[1];
		}
		vo.setHiredate(date);
		
		String result = empService.insertEmp(vo);
		if(result == null) {
			System.out.println("저장성공");
		} else {
			System.out.println("저장실패");
		}
		return "redirect:empList.do";
	}
	
	@RequestMapping(value ="empDetail.do")
	public String empDetail(EmpVO vo, Model model) throws Exception {
		EmpVO empvo = empService.viewEmpDetail(vo);
		model.addAttribute("emp", empvo);
		return "emp/empDetail";
	}
	

	@RequestMapping(value ="empModify.do")
	public String empModify(EmpVO vo, Model model) throws Exception {
		

		List<?> deptlist= sampleService.selectDeptList();
		List<?> joblist = empService.selectJobList();
		
		
		model.addAttribute("joblist",joblist);
		model.addAttribute("deptlist", deptlist);
		
		EmpVO empvo = empService.viewEmpDetail(vo);
		
		/*
		 * String date[] = empvo.getHiredate().split(" ")[0].split("-");
		 * empvo.setHiredate(date[1]+"/"+date[2]+"/"+date[0]);
		 */
		
		
		model.addAttribute("emp", empvo);
		return "emp/empModify";
	}
	
	@RequestMapping(value ="empModifySave.do")
	public String empModifySave(EmpVO vo) throws Exception {

		String date;
		if(vo.getHiredate() == null || vo.getHiredate().equals("")) {
			Calendar cal = Calendar.getInstance();
			date = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE);
		} else {
			String datelist[] = vo.getHiredate().split("/");
			date = datelist[2]+"/"+datelist[0]+"/"+datelist[1];
		}
		vo.setHiredate(date);
		
		int result = empService.updateEmp(vo);
		if(result >= 1) {
			System.out.println("수정완료");
		} else {
			System.out.println("수정 실패");
		}
		
		return "redirect:empList.do";
	}
}
