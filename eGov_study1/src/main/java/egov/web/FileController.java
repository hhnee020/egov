package egov.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egov.service.FileVO;



@Controller

public class FileController  {
	
	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;
	
	@RequestMapping(value="fileboardWrite.do")
	
	public String fileboardWrite(  FileVO vo ) throws Exception {
		
		return"admin/fileWrite";	
	} 
	
	   @RequestMapping("fileboardWriteSave.do")
	   @ResponseBody
	   public Map<String, String> insertFileboard(  MultipartHttpServletRequest multiRequest ) throws Exception {

	      MultipartFile file;
	      String uploadFile = "\\\\Mac\\Home\\Downloads\\eGovFrameDev-3.10.0-64bit\\workspace\\eGov_study1\\src\\main\\webapp\\upload" , fulldir = "", filename="";
	      int cnt = 0;
	      Map<String, String> map = new HashMap<String, String>();
	      File saveFolder = new File(uploadFile);// 물리적인 위치로 인식
	      if (!saveFolder.exists()) saveFolder.mkdirs();
	      // file :파일이름(암호화된 이름 , 오리지널이룸) , 파잉크기, 종류
	      // tmp 임시저장;
	      
	      Map<String, MultipartFile> files = multiRequest.getFileMap();
	      
	      Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	      while (itr.hasNext()) {
	         Entry<String, MultipartFile> entry = itr.next();
	         file = entry.getValue();
	         if (!"".equals(file.getOriginalFilename())) {
	            fulldir = uploadFile + "/" + file.getOriginalFilename(); // 완전한 파일 이름 과 경로
	            file.transferTo(new File(fulldir));//  원래 임시저장 되어 잇던 것을 transferTo 카피 되어 업로드
	            filename += file.getOriginalFilename() + "／";
	            cnt++;
	         }
	      }
	      map.put("filename", filename);
	      map.put("cnt", Integer.toString(cnt));
	      return map;
	   }

	
}
