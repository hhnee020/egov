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
	
	@RequestMapping("fileboardModifySave.do")
	@ResponseBody
	public String updateFileboard( MultipartHttpServletRequest multiRequest, FileVO vo )  
																		throws Exception {
		// ???????????? ???????????? ???????????? ( Max 2??? )
		String path = ps.getString("uploadDir");
		Map<String,String> map1 = uploadProcess(multiRequest,path);
		
		// ???????????? ???????????? ?????? ??? Return ?????? ???(???, ???????????????)
		String new_filename = map1.get("f1");
		
		// ???????????? ?????? ?????? ?????? ?????? ?????????????????? ?????? ??????
		String old_filename = vo.getFilename();       //   a.jpg???b.jsp???
		
		String[] ar1 = null;
		int ar1_len = 0;
		
		// ?????? ????????? ???????????? ????????? ??????
		if(new_filename != null && !new_filename.equals("")) {
			ar1 = new_filename.split("???");
			ar1_len = ar1.length;
		}
	
		// UPDATE ?????? SQL???  ?????? ?????? SQL ??????
		String up_sql = "";
	
		if(ar1_len == 1) {  //   a.jpg???b.jsp???
			new_filename = old_filename + new_filename;
			up_sql = ",filename = '"+new_filename+"'";
		} else if(ar1_len == 2) {
			up_sql = ",filename = '"+new_filename+"'";
			
			// ??????(old) ???????????? ???????????? ??????
			if(old_filename != null && !old_filename.equals("") ) {
				String[] ar2 = old_filename.split("???");
				for(int i=0; i<ar2.length; i++) {
					filedelete2(ar2[i]);
				}
			}
		}
		
		// ?????? ?????? SQL ??????
		vo.setFilename(up_sql);

		String msg = "ok";
		
		// ???????????? ????????? ??????
		// select count(*) from fileboard where unq='12' and pass='1234';
		int pass_cnt = fileService.selectFileboardPass(vo);
	
		if(pass_cnt == 0) {
			msg = "pass_fail";
		} else {
			// ???????????? ????????? ??????
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
		
		// ???????????? ????????? ??????
		vo = fileService.selectFileboardDetail(vo);
		
		model.addAttribute("vo",vo);
		return "admin/fileModify";
	}

	@RequestMapping("fileboardList.do")
	public String selectFileboardList( FileVO vo, Model model ) 
															throws Exception {
		// ??????????????? ?????? ????????????
		int page_no = vo.getPage_no();
		
		// ??????????????? ????????? ???????????? SQL??? ?????? ?????? ??????
		// 1p->1, 2p->11, 3p->21
		int s_no = (page_no-1)*10 + 1 ;
		int e_no = s_no + (10-1);
		
		// s_no ????????? e_no ????????? vo ??????
		vo.setS_no(s_no);
		vo.setE_no(e_no);
		
		// ?????? ?????? ????????? ??????
		List<?> list = fileService.selectFileboardList(vo);
		
		// ??? ????????? ?????? ?????? ????????? ?????? 
		int total = fileService.selectFileboardTotal(vo);
		
		// ??? ????????? ?????? ?????? ??????(??????)
		// 17??? (2???????????? ??????);; (double)17/10 ->ceil(1.7) -> (int)2.0  -> 2
		int total_page =  (int) Math.ceil((double)total/10);
		
		// ?????? ???????????? ?????? ?????????
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
		
		// ?????? ?????? ????????? ??????
		// pass_cnt -> 1
		
		System.out.println("unq =====> " + vo.getUnq());
		int pass_cnt = fileService.selectFileboardPass(vo);

		if( pass_cnt == 1 ) {
			// ?????? ????????? ??????
			// result -> 1
			int result = fileService.deleteFileboard(vo);
			
			if( result != 1 ) {  // ?????? ??????
				msg = "delete_fail";
			} else if( result == 1 ) {  // ?????? ??????
				
				// ???????????? ?????? ??????
				String filename = vo.getFilename();
				if( filename != null  &&  !filename.equals("") ) {
					
					String[] array = filename.split("???");
					for(int i=0; i<array.length; i++) {
						String save_dir = path;
						String full_path = save_dir+"/"+array[i];
						File file = new File(full_path);					
						file.delete();
					}
				}
			}

		} else {
			msg = "pass_fail"; // ?????? ???????????? ??????
		}
		
		return msg;
	}
	

	public static Map<String,String> uploadProcess( MultipartHttpServletRequest multiRequest , String path ) 
															throws Exception {

		MultipartFile file;
		String save_dir = path;
		String save_file  = "";
		String filenames = "";
		String filesizes = "";
		
		Map<String, String> map = new HashMap<String, String>();

		File dirname = new File(save_dir);  // ???????????? ????????? ??????
		if (!dirname.exists()) dirname.mkdirs();
		
		//   ????????? ?????? ?????? ;;;   files : ????????????(????????????,??????????????????),????????????,???????????? 
		//   /tmp ??????????????? ???????????? ( abc.jpg -> xxxefsdf.sdfdf)
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		// a.jpg  -->  /tmp/asfdasdfsadsadfxx11###.xxdfs
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				save_file = save_dir + "/" + file.getOriginalFilename();
				file.transferTo(new File(save_file));
				
				filenames += file.getOriginalFilename() + "???";
				filesizes += file.getSize() + "???";
			}
		}
		
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("f1",filenames);  //  filenames = "index.jsp???1234.PNG???";
		map1.put("f2",filesizes);  //         137???28647???
	
		return map1;
	}
	
	@RequestMapping(value = "/downloadFile.do")
	public void downloadFile(   String file,
								HttpServletResponse response) throws Exception {
		
		String path = ps.getString("uploadDir");
		
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
		
		String uploadPath = "D:/eGovFrameDev-3.10.0-64bit(2)/workspace/apple1/src/main/webapp/upload";
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
		
		// filename --> index.jsp???1234.PNG???
		// update fileboard  set filename='index.jsp???'  where unq='12';
		
		String newfilename = allfilename.replace(filename+"???","");

		Map<String,String> map = new HashMap<String,String>();
		map.put("unq",unq);
		map.put("newfilename",newfilename);
		
		int result = fileService.updateFileboardFilename(map);
		
		
		return msg;
	}
	
	
	
	@RequestMapping("passWrite.do")
	public String passWrite(	FileVO vo , 
								Model model , 
								String type,
								int fid,
								String lev  ) throws Exception {
		
		model.addAttribute("unq",vo.getUnq());
		model.addAttribute("type",type);
		model.addAttribute("fid",fid);
		model.addAttribute("lev",lev);
		model.addAttribute("filename",vo.getFilename());
		
		return "admin/passWrite";
	}
	
	
}













