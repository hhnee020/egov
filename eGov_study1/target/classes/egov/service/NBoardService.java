package egov.service;

import java.util.HashMap;
import java.util.List;

public interface NBoardService {
	
	String insertNboard(NBoardVO vo) throws Exception;
	List<?> selectBoardList(NBoardVO vo) throws Exception;
	int selectNBoardTotal(NBoardVO vo) throws Exception;
	NBoardVO selectNBoardDetail(NBoardVO vo) throws Exception;
	int updatenboard(NBoardVO vo) throws Exception;
	int deletenboard(int unq) throws Exception;
	int deleteAllnboard(HashMap<String, List<String>> map) throws Exception;

}
