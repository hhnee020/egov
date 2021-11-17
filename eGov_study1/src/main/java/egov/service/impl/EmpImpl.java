package egov.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.EmpService;
import egov.service.EmpVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("empService")
public class EmpImpl implements EmpService {

	@Resource(name="empDAO")
	private EmpDAO  empDAO;
	
	@Override
	public List<?> selectEmpList(EmpVO vo) throws Exception {
		return empDAO.selectEmpList(vo);
	}

	@Override
	public String insertEmp(EmpVO vo) throws Exception {
		return empDAO.insertEmp(vo);
	}

	@Override
	public int selectEmpTotal() throws Exception {
		return empDAO.selectEmpTotal();
	}

	@Override
	public EmpVO selectEmpDetail(int empno) throws Exception {
		return empDAO.selectEmpDetail(empno);
	}

	@Override
	public int updateEmp(EmpVO vo) throws Exception {
		return empDAO.updateEmp(vo);
	}

	@Override
	public int deleteEmp(int empno) throws Exception {
		return empDAO.deleteEmp(empno);
	}

	@Override
	public int selectEmpEmpno() throws Exception {
		return empDAO.selectEmpEmpno();
	}

	@Override
	public List<?> selectEmpJobList() throws Exception {
		
		return empDAO.selectEmpJobList();
	}
}



