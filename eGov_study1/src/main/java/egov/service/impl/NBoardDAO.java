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
		// TODO Auto-generated method stub
		return list("nboardDAO.selectNBoardList",vo);
	}

	public int selectNBoardTotal(NBoardVO vo) {
		// TODO Auto-generated method stub
		return (int)select("nboardDAO.selectNBoardTotal",vo);
	}

	public NBoardVO selectNBoardDetail(int unq) {
		// TODO Auto-generated method stub
		return (NBoardVO)select("nboardDAO.selectNBoardDetail",unq);
		
	}

	public int updateNBoard(NBoardVO vo) {
		// TODO Auto-generated method stub
		return update("nboardDAO.updateNBoard",vo);
	}

	public int deleteNBorad(NBoardVO vo) {
		// TODO Auto-generated method stub
		return delete("nboardDAO.deleteNBorad",vo);
	}

	public int deleteAllNBorad(String values) {
		// TODO Auto-generated method stub
		return delete("nboardDAO.deleteAllNBorad",values);
	}

	public int updateNBoardHits(NBoardVO vo) {
		// TODO Auto-generated method stub
		return update("nboardDAO.updateNBoardHits",vo);
	}
	
}
