package egov.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egov.service.FileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("fileDAO")
public class FileDAO extends EgovAbstractDAO {

	public String insertFileboard(FileVO vo) {
		return (String) insert("fileDAO.insertFileboard",vo);
	}

	public int selectFileTotal(FileVO vo) {
		// TODO Auto-generated method stub
		return (int) select("fileDAO.selectFileTotal",vo);
	}

	public List<?> selectFileList(FileVO vo) {
		// TODO Auto-generated method stub
		return list("fileDAO.selectFileList",vo);
	}

	public FileVO selectFileboardDetail(FileVO vo) {
		return (FileVO) select("fileDAO.selectFileboardDetail",vo);
	}
	
	public int selectFileboardPass(FileVO vo) {
		return (int) select("fileDAO.selectFileboardPass",vo);
	}

	
	public int updateFileboardFilename(Map<String, String> map) {
		return update("fileDAO.updateFileboardFilename",map);
	}

	public int updateFileboard(FileVO vo) {
		return update("fileDAO.updateFileboard",vo);
	}

}
