package egov.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.AnboardService;
import egov.service.AnboardVO;
import egov.service.CommonVO;

@Service("anboardService")
public class AnboardImpl implements AnboardService {

	@Resource(name="anboardDAO")
	AnboardDAO anboardDAO;
	
	
	@Override
	public String insertAnboard(AnboardVO vo) throws Exception {
		return anboardDAO.insertAnboard(vo);
	}

	@Override
	public List<?> selectAnboardList(CommonVO vo) throws Exception {
		return anboardDAO.selectAnboardList(vo);
	}

	@Override
	public int updateAnboard(AnboardVO vo) throws Exception {
		return anboardDAO.updateAnboard(vo);
	}

	@Override
	public int deleteAnboard(AnboardVO vo) throws Exception {
		return anboardDAO.deleteAnboard(vo);
	}

	@Override
	public AnboardVO selectAnboardDetail(AnboardVO vo) throws Exception {
		return anboardDAO.selectAnboardDetail(vo);
	}

	@Override
	public String insertAnboardReply(AnboardVO vo) throws Exception {
		return anboardDAO.insertAnboardReply(vo);
	}

	@Override
	public int selectAnboardTotal(CommonVO vo) throws Exception {
		return anboardDAO.selectAnboardTotal(vo);
	}

	@Override
	public int selectAnboardPass(AnboardVO vo) throws Exception {
		return anboardDAO.selectAnboardPass(vo);
	}

	@Override
	public int selectAnboardLev(AnboardVO vo) throws Exception {
		return anboardDAO.selectAnboardLev(vo);
	}

	@Override
	public int updateAnboardDel(AnboardVO vo) throws Exception {
		return anboardDAO.updateAnboardDel(vo);
	}

	@Override
	public void updateAnboardHits(AnboardVO vo) throws Exception {
		anboardDAO.updateAnboardHits(vo);
	}

}







