package egov.service.Impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.NBoardService;
import egov.service.NBoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("adminservice")
public class NBoardImpl extends EgovAbstractServiceImpl implements NBoardService{
	
	@Resource(name ="nboardDAO")
	NBoardDAO nboardDAO;

	@Override
	public String insertNboard(NBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.insertNBoard(vo);
	}

	@Override
	public List<?> selectBoardList(NBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.selectNBoardList(vo);
	}

	@Override
	public int selectNBoardTotal(NBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.selectNBoardTotal(vo);
	}

	@Override
	public NBoardVO selectNBoardDetail(NBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.selectNBoardDetail(vo);
	}

	@Override
	public int updatenboard(NBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.updateNBoard(vo);
	}

	@Override
	public int deletenboard(int unq) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.deleteNBoard(unq);
	}


	@Override
	public int deleteAllnboard(HashMap<String, List<String>> map) throws Exception {
		// TODO Auto-generated method stub
		return nboardDAO.deleteAllNBoard(map);
	}
	
	

}
