package egov.service;

import java.util.List;

public interface EmpService {
	
	List<?> selectEmpList(EmpVO vo) throws Exception;
	
	String insertEmp(EmpVO vo) throws Exception;
	int selectEmpTotal() throws Exception;
	EmpVO selectEmpDetail(int empno) throws Exception;
	int updateEmp(EmpVO vo) throws Exception;
 	int deleteEmp(int empno) throws Exception;
 	/*
 	 * 사원번호출력
 	 */
 	int selectEmpEmpno() throws Exception;
 	//List<?> selectDeptList() throws Exception;
 	List<?> selectEmpJobList() throws Exception;
 	
}
