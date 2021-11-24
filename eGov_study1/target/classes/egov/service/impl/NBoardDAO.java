package egov.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.NBoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("nboardDAO")
public class NBoardDAO extends EgovAbstractDAO{

	public String insertNBoard(NBoardVO vo) {
		// TODO Auto-generated method stub
		return (String) insert("nboardDAO.insertNBoard",vo);
	}

	public List<?> selectNBoardList(NBoardVO vo) {
		// TODO Auto-generated method stub
		return list("nboadDAO.selectNBoardList",vo);
	}

	public int selectNBoardTotal(NBoardVO vo) {
		// TODO Auto-generated method stub
		return (int) select("nboardDAO.selectNBoardTotal",vo);
	}

	public NBoardVO selectNBoardDetail(NBoardVO vo) {
		// TODO Auto-generated method stub
		return (NBoardVO) select("nboardDAO.selectNBoardDetail",vo);
	}

	public int updateNBoard(NBoardVO vo) {
		// TODO Auto-generated method stub
		return (int)update("nboardDAO.updateNBoard",vo);
	}

	public int deleteNBoard(int unq) {
		// TODO Auto-generated method stub
		return (int) delete("nboardDAO.deleteNBoard",unq);
	}

	public int deleteAllNBoard(HashMap<String, List<String>> map) {
		// TODO Auto-generated method stub
		return (int) delete("nboardDAO.deleteAllNBoard",map);
						
	}
	

}
