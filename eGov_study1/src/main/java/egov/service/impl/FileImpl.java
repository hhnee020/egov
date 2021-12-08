package egov.service.Impl;

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
	public int selectFileTotal(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.selectFileTotal(vo);
	}

	@Override
	public List<?> selectFileList(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.selectFileList(vo);
	}

	@Override
	public FileVO selectFileboardDetail(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return fileDAO.deleteFileboard(vo);
	}

	
	
}
