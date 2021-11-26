package egov.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.NBoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("nboardDAO")
public class NBoardDAO  extends EgovAbstractDAO {

	public String insertNBoard(NBoardVO vo) {
		return (String) insert("nboardDAO.insertNBoard",vo);
	}

	public List<?> selectNBoardList(NBoardVO vo) {		
		return list("nboardDAO.selectNBoardList",vo);
	}

	public int selectNBoardTotal(NBoardVO vo) {
		return (int) select("nboardDAO.selectNBoardTotal",vo);
	}

	public NBoardVO selectNBoardDetail(int unq) {
		return (NBoardVO) select("nboardDAO.selectNBoardDetail",unq);
	}

	public int updateNBoard(NBoardVO vo) {
		return update("nboardDAO.updateNBoard",vo);
	}

	public int deleteNBoard(NBoardVO vo) {
		return delete("nboardDAO.deleteNBoard",vo);
	}

	public int deleteNBoardAll(String values) {
		return delete("nboardDAO.deleteNBoardAll",values);
	}

	public int updateNBoardHits(NBoardVO vo) {
		return update("nboardDAO.updateNBoardHits",vo);
	}
	
}






