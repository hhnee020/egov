package egov.service.impl;

import org.springframework.stereotype.Repository;

import egov.service.FileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
//
@Repository("fileDAO") // fileDAO을 찾을때 여기로 연결
public class FileDAO  extends EgovAbstractDAO {

	public String insertFileboard(FileVO vo) {
		// TODO Auto-generated method stub
		return (String) insert("fileDAO.insertFileboard",vo);
	}

}
