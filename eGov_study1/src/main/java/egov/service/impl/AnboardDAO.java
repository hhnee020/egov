package egov.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.AnboardVO;
import egov.service.CommonVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("anboardDAO")
public class AnboardDAO  extends EgovAbstractDAO {

	public String insertAnboard(AnboardVO vo) {
		return (String) insert("anboardDAO.insertAnboard",vo);
	}

	public List<?> selectAnboardList(CommonVO vo) {
		return list("anboardDAO.selectAnboardList",vo);
	}

	public AnboardVO selectAnboardDetail(AnboardVO vo) {
		return (AnboardVO) select("anboardDAO.selectAnboardDetail",vo);
	}

	public String insertAnboardReply(AnboardVO vo) {
		return (String) insert("anboardDAO.insertAnboardReply",vo);
	}

	public int selectAnboardTotal(CommonVO vo) {
		return (int) select("anboardDAO.selectAnboardTotal",vo);
	}

	public int deleteAnboard(AnboardVO vo) {
		return delete("anboardDAO.deleteAnboard",vo);
	}

	public int selectAnboardPass(AnboardVO vo) {
		return (int) select("anboardDAO.selectAnboardPass",vo);
	}

	public int selectAnboardLev(AnboardVO vo) {
		return (int) select("anboardDAO.selectAnboardLev",vo);
	}

	public int updateAnboardDel(AnboardVO vo) {
		return update("anboardDAO.updateAnboardDel",vo);
	}

	public int updateAnboard(AnboardVO vo) {
		return update("anboardDAO.updateAnboard",vo);
	}

	public void updateAnboardHits(AnboardVO vo) {
		update("anboardDAO.updateAnboardHits",vo);
	}
	

}






