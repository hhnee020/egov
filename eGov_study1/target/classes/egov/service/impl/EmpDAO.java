package egov.service.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.EmpVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("empDAO")
public class EmpDAO extends EgovAbstractDAO{

	public List<?> selectEmpList(EmpVO vo) {
//		System.out.println("select DAO");
		// TODO Auto-generated method stub
		return list("empDAO.selectEmpList",vo);
	}

	public String insertEmp(EmpVO vo) {
		// TODO Auto-generated method stub
		return (String) insert("empDAO.insertEmp",vo);
	}

	public EmpVO viewEmpDetail(EmpVO vo) {
		// TODO Auto-generated method stub
		return (EmpVO) select("empDAO.viewEmpDetail",vo);
	}

	public int selectEmpTotals() {
		// TODO Auto-generated method stub
		System.out.println("DAO 접근");
		int testDAO = (int) select("empDAO.selectEmpTotals");
//		return (int) select("empDAO.selectEmpTotals");
		System.out.println("DAO : " + testDAO);
		return testDAO;
	}

	public int updateEmp(EmpVO vo) {
		// TODO Auto-generated method stub
		return update("empDAO.updateEmp",vo);
	}

	public int maxcount() {
		// TODO Auto-generated method stub
		return (int) select("empDAO.maxcount");
	}

	public List<?> selectJobList() {
		// TODO Auto-generated method stub
		return list("empDAO.selectJobList");
	}


}
