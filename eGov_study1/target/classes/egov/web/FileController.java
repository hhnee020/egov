package egov.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import egov.service.FileService;
import egov.service.FileVO;


@Controller
public class FileController {

	
	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;
	
	@Resource(name= "fileService")
	FileService fileService;
	
	@RequestMapping(name = "fileboardList.do")
	public String fileboardList(FileVO vo, Model model) throws Exception {
		System.out.println("dwad");
		
		int page_no = vo.getPageNo(), s_no = (page_no-1)*10+1, e_no = s_no+(10-1);
		
		vo.setsNo(s_no);
		vo.seteNo(e_no);
		
		List<?> result = fileService.selectFileList(vo);
		int total = fileService.selectFileTotal(vo);
		
		int total_page = (int) Math.ceil((double) total/10);
		int rownum = total - (page_no-1)*10;
		vo.setRownum(rownum);
		vo.setTotalpage(total_page);
		vo.setTotal(total);
		model.addAttribute("vo", vo);
		model.addAttribute("result", result);
		
		return "admin/fileList";
	}
	
	@RequestMapping("fileboardWrite.do")
	public String fileboardWrite() {

		return "admin/fileWrite";
	}

	@RequestMapping("fileboardWriteSave.do")
	@ResponseBody
	public String insertFileboard(FileVO vo,  MultipartHttpServletRequest multiRequest ) throws Exception {
		Map<String,String> map = uploadProcess(multiRequest);
		vo.setFilename(map.get("filename"));
		vo.setFilesize(map.get("filesize"));
		String result = fileService.insertFileboard(vo);
		String msg = "ok";
		if(result != null) msg ="fail";
		return msg;
	}

	@RequestMapping("fileboardWriteSave1.do")
	@ResponseBody
	public ModelAndView insertFileboard(  MultipartHttpServletRequest multiRequest ) throws Exception {
		MultipartFile file;
		String uploadFile = "/Users/hani/eclipse-workspace/apple1/src/main/webapp/upload" , fulldir = "", filename="";
		int cnt = 0;
		Map<String, String> map = new HashMap<String, String>();
		
		
		File saveFolder = new File(uploadFile);  // 물리적인 위치로 인식
		if (!saveFolder.exists()) saveFolder.mkdirs();
		
		//   업로드 파일 인식 ;;;   files : 파일이름(오리지널,암호화된이름),파일크기,파일종류 
		//   /tmp 디렉토리에 임시시장 ( abc.jpg -> xxxefsdf.sdfdf)
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		// 다중
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				fulldir = uploadFile + "/" + file.getOriginalFilename();
				file.transferTo(new File(fulldir));
				filename += file.getOriginalFilename() + "／";
				cnt++;
			}
		}
		map.put("filename", filename);
		map.put("cnt", Integer.toString(cnt));
		

	    ModelAndView mo = new ModelAndView("jsonView");
	    mo.addObject("json", new JSONObject(map));
		return mo;
	}
	
	
	public static Map<String,String> uploadProcess( MultipartHttpServletRequest multiRequest ) 
			throws Exception {
		MultipartFile file;
		String save_dir = "/Users/hani/eclipse-workspace/apple1/src/main/webapp/upload";
		String save_file  = "";
		String filenames = "";
		String filesizes = "";
		
		Map<String, String> map = new HashMap<String, String>();
		
		File dirname = new File(save_dir);  // 물리적인 위치로 인식
		if (!dirname.exists()) dirname.mkdirs();
		
		//   업로드 파일 인식 ;;;   files : 파일이름(오리지널,암호화된이름),파일크기,파일종류 
		//   /tmp 디렉토리에 임시시장 ( abc.jpg -> xxxefsdf.sdfdf)
Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		// 다중
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				save_file = save_dir + "/" + file.getOriginalFilename();
				file.transferTo(new File(save_file));
				filenames += file.getOriginalFilename() + "／";
				filesizes += file.getSize() + "／";
			}
		}
		
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("f1",filenames);  //   index.jsp／1234.PNG／
		map1.put("f2",filesizes);  //         137／28647／
	
		return map1;
	}
	
	@RequestMapping(value = "/downloadFile.do")
	   public void downloadFile(@RequestParam(value = "requestedFile") String requestedFile,
	            HttpServletResponse response) throws Exception {
	      String uploadPath = "/Users/hani/eclipse-workspace/apple1/src/main/webapp/upload";
	      File uFile = new File(uploadPath, requestedFile);
	      int fSize = (int) uFile.length();

	      if (fSize > 0) {
	         BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
	         // String mimetype = servletContext.getMimeType(requestedFile);
	         String mimetype = "text/html";
	         response.setBufferSize(fSize);
	         response.setContentType(mimetype);
	         response.setHeader("Content-Disposition", "attachment; filename=\"" + requestedFile + "\"");
	         response.setContentLength(fSize);
	         FileCopyUtils.copy(in, response.getOutputStream());
	         in.close();
	         response.getOutputStream().flush();
	         response.getOutputStream().close();
	      } else {
	         //setContentType을 프로젝트 환경에 맞추어 변경
	         response.setContentType("application/x-msdownload");
	         PrintWriter printwriter = response.getWriter();
	         printwriter.println("<html>");
	         printwriter.println("<br><br><br><h2>Could not get file name:<br>"+ requestedFile + "</h2>");
	         printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
	         printwriter.println("<br><br><br>© webAccess");
	         printwriter.println("</html>");
	         printwriter.flush();
	         printwriter.close();
	      }
	      
	      
	      
	   }
	
	@RequestMapping("fileboardDetail.do")
	public String selectFileboardDetail( FileVO vo, Model model ) throws Exception {
		
		// 상세보기 서비스 실행
		vo = fileService.selectFileboardDetail(vo);
		
		String content = vo.getContent();
		if( content != null ) {
			content = content.replace("\n", "<br>"); // 줄바꿈 
			content = content.replace(" ", "&nbsp;"); // 빈칸 허용 
			vo.setContent(content);
		}

		// 화면으로 전송                    
		model.addAttribute("vo",vo);
		
		return "admin/fileDetail";
		
		
	}
	
	@RequestMapping("fileboardModify.do")
	public String selectFileModify (FileVO vo ,Model model) throws Exception {
		
		// 상세보기  
		
		vo = fileService.selectFileboardDetail(vo);
		
		model.addAttribute("vo",vo);
		
		return "admin/fileModify";
	}
	
	@RequestMapping("fileboardModifySave.do")
	@ResponseBody
	public String updateFileboard( MultipartHttpServletRequest multiRequest, FileVO vo )  
																		throws Exception {
		// 물리적인 데이터의 저장처리 ( Max 2개 )
		Map<String,String> map1 = uploadProcess(multiRequest);
		
		// 물리적인 데이터의 저장 후 Return 받은 값(즉, 파일이름)
		String new_filename = map1.get("f1");
		
		// 화면으로 부터 전달 받은 이미 등록되어있는 파일 이름
		String old_filename = vo.getFilename();       //   a.jpg／b.jsp／
		
		String[] ar1 = null;
		int ar1_len = 0;
		
		// 새로 등록된 물리적인 파일의 개수
		if(new_filename != null && !new_filename.equals("")) {
			ar1 = new_filename.split("／");
			ar1_len = ar1.length;
		}
	
		// UPDATE 관련 SQL의  중간 삽입 SQL 제작
		String up_sql = "";
	
		if(ar1_len == 1) {  //   a.jpg／b.jsp／
			new_filename = old_filename + new_filename;
			up_sql = ",filename = '"+new_filename+"'";
		} else if(ar1_len == 2) {
			up_sql = ",filename = '"+new_filename+"'";
			
			// 기존(old) 물리적인 데이터의 삭제
			if(old_filename != null && !old_filename.equals("") ) {
				String[] ar2 = old_filename.split("／");
				for(int i=0; i<ar2.length; i++) {
					filedelete2(ar2[i]);
				}
			}
		}
		
		// 중간 삽입 SQL 세팅
		vo.setFilename(up_sql);

		String msg = "ok";
		
		// 암호확인 서비스 실행
		// select count(*) from fileboard where unq='12' and pass='1234';
		int pass_cnt = fileService.selectFileboardPass(vo);
	
		if(pass_cnt == 0) {
			msg = "pass_fail";
		} else {
			// 수정처리 서비스 실행
			int result = fileService.updateFileboard(vo);
			if(result == 0) {
				msg = "save_fail";
			}
		}

		return msg;
	}
	
	
	
	@RequestMapping(value = "/filedelete.do")
	@ResponseBody
	public String filedelete( String allfilename, String filename, String unq ) throws Exception {
		
		String msg = "ok";
		// unq = "12";
		// filename = "1234.PNG";
		String uploadPath = "/Users/hani/eclipse-workspace/apple1/src/main/webapp/upload";
		String fullPath = uploadPath + "/" + filename;
		File file = new File(fullPath);					
		file.delete();
		
		// filename --> index.jsp／1234.PNG／
		// update fileboard  set filename='index.jsp／'  where unq='12';
		
		String newfilename = allfilename.replace(filename+"／","");

		Map<String,String> map = new HashMap<String,String>();
		map.put("unq",unq);
		map.put("newfilename",newfilename);
		
		int result = fileService.updateFileboardFilename(map);
		
		
		return msg;
	}
	
	public static void filedelete2(String filename) throws Exception {
		String uploadPath = "/Users/hani/eclipse-workspace/apple1/src/main/webapp/upload";
		String fullPath = uploadPath + "/" + filename;
		File file = new File(fullPath);					
		file.delete();
	}
	
	
	}
	




