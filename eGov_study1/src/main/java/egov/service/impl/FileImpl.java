package egov.service.impl;

import java.util.List;
import java.util.Map;

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
		return fileDAO.insertFileboard(vo);
	}

	@Override
	public List<?> selectFileboardList(FileVO vo) throws Exception {
		return fileDAO.selectFileboardList(vo);
	}

	@Override
	public int selectFileboardTotal(FileVO vo) throws Exception {
		return fileDAO.selectFileboardTotal(vo);
	}

	@Override
	public FileVO selectFileboardDetail(FileVO vo) throws Exception {
		return fileDAO.selectFileboardDetail(vo);
	}

	@Override
	public int updateFileboardFilename(Map<String, String> map) throws Exception {
		return fileDAO.updateFileboardFilename(map);
	}

	@Override
	public int selectFileboardPass(FileVO vo) throws Exception {
		return fileDAO.selectFileboardPass(vo);
	}

	@Override
	public int updateFileboard(FileVO vo) throws Exception {
		return fileDAO.updateFileboard(vo);
	}

	@Override
	public int deleteFileboard(FileVO vo) throws Exception {
		return fileDAO.deleteFileboard(vo);
	}


}




