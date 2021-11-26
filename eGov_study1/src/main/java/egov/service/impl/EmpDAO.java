package egov.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.EmpVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("empDAO")
public class EmpDAO  extends EgovAbstractDAO  {
	public List<?> selectEmpList(EmpVO vo) {
		return list("empDAO.selectEmpList",vo);
	}

	public String insertEmp(EmpVO vo) {
		return (String) insert("empDAO.insertEmp",vo);
	}

	public int selectEmpTotal() {
		return (int) select("empDAO.selectEmpTotal");
	}

	public EmpVO selectEmpDetail(int empno) {
		return (EmpVO) select("empDAO.selectEmpDetail",empno);
	}

	public int updateEmp(EmpVO vo) {
		return update("empDAO.updateEmp",vo);
	}

	public int deleteEmp(int empno) {
		return delete("empDAO.deleteEmp",empno);
	}

	public int selectEmpEmpno() {
		return (int) select("empDAO.selectEmpEmpno");
	}

	public List<?> selectEmpJobList() {
		return list("empDAO.selectEmpJobList");
	}
	
}





