package egov.service.impl;

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

	public List<?> selectFileboardList(FileVO vo) {
		return list("fileDAO.selectFileboardList",vo);
	}

	public int selectFileboardTotal(FileVO vo) {
		return (int) select("fileDAO.selectFileboardTotal",vo);
	}

	public FileVO selectFileboardDetail(FileVO vo) {
		return (FileVO) select("fileDAO.selectFileboardDetail",vo);
	}

	public int updateFileboardFilename(Map<String, String> map) {
		return update("fileDAO.updateFileboardFilename",map);
	}

	public int selectFileboardPass(FileVO vo) {
		return (int) select("fileDAO.selectFileboardPass",vo);
	}

	public int updateFileboard(FileVO vo) {
		return update("fileDAO.updateFileboard",vo);
	}

	public int deleteFileboard(FileVO vo) {
		return delete("fileDAO.deleteFileboard",vo);
	}

}

