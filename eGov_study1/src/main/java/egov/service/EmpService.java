package egov.service;

import java.util.List;

public interface EmpService {
	
	List<?> selectEmpList(EmpVO vo) throws Exception;

	String insertEmp(EmpVO vo) throws Exception;

	EmpVO viewEmpDetail(EmpVO vo) throws Exception;
	
	int selectEmpTotal() throws Exception;

	int updateEmp(EmpVO vo) throws Exception;
	
	int maxcount() throws Exception;

	List<?> selectJobList() throws Exception;
	
}
