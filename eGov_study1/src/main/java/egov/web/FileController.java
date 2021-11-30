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
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egov.service.FileService;
import egov.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class FileController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService ps;

	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;
	
	@Resource(name = "fileService")
	FileService fileService;
	
	@RequestMapping("fileboardWrite.do")
	public String fileboardWrite() {

		//uploadDir
		String path = ps.getString("uploadDir");
		//System.out.println(path);
		
		return "admin/fileWrite";
	}

	@RequestMapping("fileboardWriteSave.do")
	@ResponseBody
	public String insertFileboard( MultipartHttpServletRequest multiRequest, FileVO vo ) 
																			throws Exception {
		
		String path = ps.getString("uploadDir");
		
		Map<String,String> map1 = uploadProcess(multiRequest,path);
		
		vo.setFilename(map1.get("f1"));
		vo.setFilesize(map1.get("f2"));
		
		String result = fileService.insertFileboard(vo);
	
		String message = "ok";
		
		if( result != null ) message = "error"; 
		//map.put("filename", filename);
		//map.put("cnt", Integer.toString(cnt));
		//System.out.println("filename :::: " + filename);

		return message;
	}
	
	
	/// 파일 저장 ;;
	
	@RequestMapping("fileboardModifySave.do")
	@ResponseBody
	public String updateFileboard( MultipartHttpServletRequest multiRequest, FileVO vo )  
																		throws Exception {
		// 물리적인 데이터의 저장처리 ( Max 2개 )
		String path = ps.getString("uploadDir");
		Map<String,String> map1 = uploadProcess(multiRequest,path);
		
		// 물리적인 데이터의 저장 후 Return 받은 값(즉, 파일이름들)
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
	
	@RequestMapping("fileboardDetail.do")
	public String selectFileDetail(FileVO vo, Model model) throws Exception{
		
		vo = fileService.selectFileboardDetail(vo);
		
		String content = vo.getContent();
		if(content != null ) {
			content = content.replace("\n","<br>");
			content = content.replace(" ","&nbsp;");
			vo.setContent(content);
		}
		
		model.addAttribute("vo",vo);
		return "admin/fileDetail";	
	}

	@RequestMapping("fileboardModify.do")
	public String fileboardModify(FileVO vo, Model model) throws Exception {
		
		// 상세보기 서비스 실행
		vo = fileService.selectFileboardDetail(vo);
		
		model.addAttribute("vo",vo);
		return "admin/fileModify";
	}
	
	
 /// 파일 리스트 출력
	@RequestMapping("fileboardList.do")
	public String selectFileboardList( FileVO vo, Model model ) 
															throws Exception {
		// 출력페이지 번호 가져오기
		int page_no = vo.getPage_no();
		
		// 출력페이지 번호를 이용하여 SQL의 출력 범위 설정
		// 1p->1, 2p->11, 3p->21
		int s_no = (page_no-1)*10 + 1 ;
		int e_no = s_no + (10-1);
		
		// s_no 변수와 e_no 변수의 vo 세팅
		vo.setS_no(s_no);
		vo.setE_no(e_no);
		
		// 목록 출력 서비스 실행
		List<?> list = fileService.selectFileboardList(vo);
		
		// 총 데이터 값을 얻는 서비스 실행 
		int total = fileService.selectFileboardTotal(vo);
		
		// 총 페이지 값을 얻는 설정(세팅)
		// 17개 (2페이지의 결과);; (double)17/10 ->ceil(1.7) -> (int)2.0  -> 2
		int total_page =  (int) Math.ceil((double)total/10);
		
		// 출력 페이지의 시작 행번호
		int rownum = total - (page_no-1)*10;
		
		vo.setTotal(total);
		vo.setTotal_page(total_page);
		vo.setRownum(rownum);

		model.addAttribute("vo",vo);
		model.addAttribute("list",list);
		
		return "admin/fileList";
	}

	// 
	@RequestMapping("fileboardDelete.do")
	@ResponseBody
	public String deleteFileboard( FileVO vo ) throws Exception {
		
		String path = ps.getString("uploadDir");
		
		String msg = "ok";
		
		// 암호 확인 서비스 실행
		// pass_cnt -> 1
		
		System.out.println("unq =====> " + vo.getUnq());
		int pass_cnt = fileService.selectFileboardPass(vo);

		if( pass_cnt == 1 ) {
			// 삭제 서비스 실행
			// result -> 1 
			int result = fileService.deleteFileboard(vo);
			
			if( result != 1 ) {  // 삭제 실패
				msg = "delete_fail";
			} else if( result == 1 ) {  // 삭제 성공
				
				// 물리적인 파일 삭제
				String filename = vo.getFilename();
				if( filename != null  &&  !filename.equals("") ) { // 파일이 있을 경
				
					String[] array = filename.split("／");
					for(int i=0; i<array.length; i++) {
						String save_dir = path;
						String full_path = save_dir+"/"+array[i];
						File file = new File(full_path);					
						file.delete();
					}
				}
			}

		} else {
			msg = "pass_fail"; // 암호 일치하지 않음
		}
		
		return msg;
	}
	

	public static Map<String,String> uploadProcess( MultipartHttpServletRequest multiRequest , String path ) 
															throws Exception { // 업로드 

		MultipartFile file;
		String save_dir = path;
		String save_file  = "";
		String filenames = "";
		String filesizes = "";
		
		Map<String, String> map = new HashMap<String, String>();

		File dirname = new File(save_dir);  // 물리적인 위치로 인식
		if (!dirname.exists()) dirname.mkdirs();
		
		//   업로드 파일 인식 ;;;   files : 파일이름(오리지널,암호화된이름),파일크기,파일종류 
		//   /tmp 디렉토리에 임시시장 ( abc.jpg -> xxxefsdf.sdfdf)
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		// a.jpg  -->  /tmp/asfdasdfsadsadfxx11###.xxdfs
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
		map1.put("f1",filenames);  //  filenames = "index.jsp／1234.PNG／";
		map1.put("f2",filesizes);  //         137／28647／
	
		return map1;
	}
	
	@RequestMapping(value = "/downloadFile.do")
	public void downloadFile(   String file,
								HttpServletResponse response) throws Exception { // 다운로드;;
		
		String path = ps.getString("uploadDir"); //  파일 주소 
		
		String uploadPath = path;
		File uFile = new File(uploadPath, file);
		int fSize = (int) uFile.length();

		if (fSize > 0) {
		
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
			String mimetype = "text/html";
			
			response.setBufferSize(fSize);
			response.setContentType(mimetype);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
			response.setContentLength(fSize);
			FileCopyUtils.copy(in, response.getOutputStream());
			in.close();
			
			response.getOutputStream().flush();
		}
	}
	
	
	public static void filedelete2(String filename) throws Exception {
		
		//String path = ps.getString("uploadDir");
		
		String uploadPath = "/Users/hani/Downloads";
		String fullPath = uploadPath + "/" + filename;
		File file = new File(fullPath);					
		file.delete();
	}
	

	@RequestMapping(value = "/filedelete.do")
	@ResponseBody
	public String filedelete( String allfilename, String filename, String unq ) throws Exception {
		
		String path = ps.getString("uploadDir");
		
		String msg = "ok";
		// unq = "12";
		// filename = "1234.PNG";
		String uploadPath = path;
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
	
	@RequestMapping("passWrite.do")
	public String passWrite(FileVO vo , Model model , String type) throws Exception {
		
		model.addAttribute("unq",vo.getUnq());
		model.addAttribute("type", type);
		model.addAttribute("filename",vo.getFilename());
		return "admin/passWrite";
	}
	
	
}













