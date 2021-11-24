package egov.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.EmpService;
import egov.service.EmpVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("empService")
public class EmpImpl extends EgovAbstractServiceImpl implements EmpService{

	@Resource(name="empDAO")
	private EmpDAO empDAO;
	
	@Override
	public List<?> selectEmpList(EmpVO vo) throws Exception {
//		System.out.println("select Impl");
		// TODO Auto-generated method stub
		return empDAO.selectEmpList(vo);
	}

	@Override
	public String insertEmp(EmpVO vo) throws Exception {
		// TODO Auto-generated method stub
		return empDAO.insertEmp(vo);
	}

	@Override
	public EmpVO viewEmpDetail(EmpVO vo) throws Exception {
		// TODO Auto-generated method stub
		return empDAO.viewEmpDetail(vo);
	}

	@Override
	public int selectEmpTotal() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("impl 접근");
		int testimpl = empDAO.selectEmpTotals();
		System.out.println("impl : " + testimpl );
//		return empDAO.selectEmpTotals();
		return testimpl;
	}

	@Override
	public int updateEmp(EmpVO vo) throws Exception {
		// TODO Auto-generated method stub
		return empDAO.updateEmp(vo);
	}

	@Override
	public int maxcount() throws Exception {
		// TODO Auto-generated method stub
		return empDAO.maxcount();
	}

	@Override
	public List<?> selectJobList() throws Exception {
		// TODO Auto-generated method stub
		return empDAO.selectJobList();
	}


}
