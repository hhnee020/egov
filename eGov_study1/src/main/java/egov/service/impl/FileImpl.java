package egov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.FileService;
import egov.service.FileVO;

@Service("fileService")
public class FileImpl implements FileService {

	@Resource(name="fileDAO")
	FileDAO fileDAO;
	
	@Override
	public String insertFileboard(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.insertFileboard(vo);
	}

}
