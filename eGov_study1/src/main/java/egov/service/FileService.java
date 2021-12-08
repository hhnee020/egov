package egov.service;

import java.util.List;
import java.util.Map;

public interface FileService {

	String insertFileboard( FileVO vo ) throws Exception;

	int selectFileTotal(FileVO vo) throws Exception;

	List<?> selectFileList(FileVO vo)throws Exception;

	FileVO selectFileboardDetail(FileVO vo) throws Exception;

	int updateFileboardFilename(Map<String, String> map) throws Exception;
	
	int selectFileboardPass(FileVO vo) throws Exception;
	int updateFileboard(FileVO vo) throws Exception;

	int deleteFileboard(FileVO vo) throws Exception;
}
