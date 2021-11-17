package egov.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.EmpVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("empDAO")
public class EmpDAO extends EgovAbstractDAO {

	public List<?> selectEmpList( EmpVO vo) {
		// TODO Auto-generated method stub
		return list("empDAO.selectEmpList" , vo);
	}

	public String insertEmp(EmpVO vo) {
		// TODO Auto-generated method stub
		return (String) insert("empDAO.insertEmp" , vo );
	}

	public int selectEmpTotal() {
		// TODO Auto-generated method stub
		return (int)select("empDAO.selectEmpTotal");
	}

	public EmpVO selectEmpDetail( int empno ) {
		// TODO Auto-generated method stub
		return (EmpVO)select("empDAO.selectEmpDetail" ,empno);
	} 

	public int updateEmp(EmpVO vo) {
		// TODO Auto-generated method stub
		return (int)update("empDAO.updateEmp" ,vo);
	}

	public int deleteEmp(int empno) {
		// TODO Auto-generated method stub
		return delete("empDAO.deleteEmp" , empno);
	}


	public List<?> selectEmpJobList() {
		// TODO Auto-generated method stub
		return  list("empDAO.selectEmpJobList" );
	}

	public int selectEmpEmpno() {
		// TODO Auto-generated method stub
		return (int)select("empDAO.selectEmpEmpno");
	}

}
