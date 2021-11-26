package egov.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.NBoardService;
import egov.service.NBoardVO;

@Service("nboardService")
public class NBoardImpl implements NBoardService {

	@Resource(name="nboardDAO")
	NBoardDAO nboardDAO;
	
	@Override
	public String insertNBoard(NBoardVO vo) throws Exception {
		return  nboardDAO.insertNBoard(vo);
	}

	@Override
	public List<?> selectNBoardList(NBoardVO vo) throws Exception {
		return  nboardDAO.selectNBoardList(vo);
	}

	@Override
	public int selectNBoardTotal(NBoardVO vo) throws Exception {
		return nboardDAO.selectNBoardTotal(vo);
	}

	@Override
	public NBoardVO selectNBoardDetail(int unq) throws Exception {
		return nboardDAO.selectNBoardDetail(unq);
	}

	@Override
	public int updateNBoard(NBoardVO vo) throws Exception {
		return nboardDAO.updateNBoard(vo);
	}

	@Override
	public int deleteNBoard(NBoardVO vo) throws Exception {
		return nboardDAO.deleteNBoard(vo);
	}

	@Override
	public int deleteNBoardAll(String values) throws Exception {
		return nboardDAO.deleteNBoardAll(values);
	}

	@Override
	public int updateNBoardHits(NBoardVO vo) throws Exception {
		return nboardDAO.updateNBoardHits(vo);
	}
	
}




