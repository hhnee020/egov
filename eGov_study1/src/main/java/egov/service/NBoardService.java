package egov.service;

import java.util.List;

public  interface  NBoardService {

	String  insertNBoard(NBoardVO vo) throws Exception;
	List<?> selectNBoardList(NBoardVO vo) throws Exception;
	int selectNBoardTotal(NBoardVO vo) throws Exception;
	NBoardVO selectNBoardDetail(int unq) throws Exception;
	int updateNBoard(NBoardVO vo) throws Exception;
	int deleteNBoard(NBoardVO vo) throws Exception;
	int deleteNBoardAll(String values) throws Exception;
	
	/*
	 * 조회수 증가 
	 */
	int updateNBoardHits(NBoardVO vo) throws Exception;
}
