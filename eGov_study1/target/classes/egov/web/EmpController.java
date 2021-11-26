package egov.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egov.service.EmpService;
import egov.service.EmpVO;
import egov.service.impl.EmpImpl;
import egovframework.example.sample.service.EgovSampleService;

@Controller
public class EmpController {

	@Resource(name="empService")
	private EmpService empService;

	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	@RequestMapping(value="empList.do")
	public String selectEmpList( EmpVO vo, Model model ) 
														throws Exception {
		// 현페이지번호
		int page_no  = vo.getPage_no();
		
		// (1)->1 , (2)->11 , (3)->21
		int start_no = (page_no-1)*10 + 1; 
		int end_no   = start_no+9;

		vo.setStart_no(start_no); // SQL 시작번호
		vo.setEnd_no(end_no);     // SQL 끝번호

		List<?> list = empService.selectEmpList(vo);
		int total    = empService.selectEmpTotal();

		int totalPage = (int) Math.ceil( (double)total/10 );
		
		// (1)-13, (2)-3
		int rownum = total - (page_no-1)*10; 

		model.addAttribute("totalPage",totalPage); // 총 페이지 번호
		model.addAttribute("total",total);    // 총 데이터 값
		model.addAttribute("list",list);      // 페이지 출력 목록
		model.addAttribute("rownum",rownum);  // 출력 페이지 행 시작 번호
		return "emp/empList";
	}

	@RequestMapping(value="empWrite.do")
	public String empWrite(Model model) throws Exception {
		
		int empno = empService.selectEmpEmpno();
		model.addAttribute("empno",empno);
		
		List<?> deptList = sampleService.selectDeptList();
		model.addAttribute("deptList",deptList);

		List<?> jobList = empService.selectEmpJobList();
		model.addAttribute("jobList",jobList);
		
		return "emp/empWrite";
	}

	@RequestMapping(value="empWriteSave.do")
	public String insertEmp(EmpVO vo) throws Exception {
		
		// 월/일/년->년/월/일
		String date1 = vo.getHiredate();
		if( date1 != null && !date1.equals("") ) {
			String[] array = date1.split("/");
			date1 = array[2]+"/"+array[0]+"/"+array[1];
			vo.setHiredate(date1);
		}
		
		String result = empService.insertEmp(vo);
		
		
		return "redirect:empList.do";
	}
	
	@RequestMapping("empModify.do")
	public String selectEmpModify(int empno , Model model) throws Exception {
		
		List<?> deptList = sampleService.selectDeptList();
		model.addAttribute("deptList",deptList);

		List<?> jobList = empService.selectEmpJobList();
		model.addAttribute("jobList",jobList);
		
		EmpVO vo = empService.selectEmpDetail(empno);
		model.addAttribute("vo",vo);
		
		return "emp/empModify";
	}
	
	@RequestMapping("empModifySave.do")
	public String updateEmp( EmpVO vo ) throws Exception {
		
		// 월/일/년->년/월/일
		String date1 = vo.getHiredate();
		if( date1 != null && !date1.equals("") ) {
			String[] array = date1.split("/");
			date1 = array[2]+"/"+array[0]+"/"+array[1];
			vo.setHiredate(date1);
		}
		int result = empService.updateEmp(vo);
		
		System.out.println("결과 : " +  result);
		
		/*
		 * if( result == 1 ) { System.out.println("수정 처리 완료"); } else {
		 * System.out.println("수정 처리 실패"); }
		 */
		
		return "redirect:empList.do";
	}
	
	@RequestMapping("empDelete.do")
	public String deleteEmp(int empno) throws Exception {
		
		int result = empService.deleteEmp(empno);
		
		if( result == 1 ) {
			System.out.println("삭제 처리 완료"); }
		else {
			System.out.println("삭제 처리 실패");
		}
			 
		return "redirect:empList.do";
	}
	
	
	
}



