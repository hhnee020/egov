package egov.service;

import java.util.List;

public interface AnboardService {

	// 저장 처리
	String insertAnboard(AnboardVO vo) throws Exception;
	
	// 목록 화면 처리
	List<?> selectAnboardList(CommonVO vo) throws Exception;
	
	// 수정 처리
	int updateAnboard(AnboardVO vo) throws Exception;
	
	// 삭제 처리
	int deleteAnboard(AnboardVO vo) throws Exception;

	// 상세 보기 처리
	AnboardVO selectAnboardDetail(AnboardVO vo) throws Exception;

	// 답변 저장 처리
	String insertAnboardReply(AnboardVO vo) throws Exception;

	// 총 데이터 개수 출력
	int selectAnboardTotal(CommonVO vo) throws Exception;

	// 암호 일치 검사
	int selectAnboardPass(AnboardVO vo) throws Exception;

	// 하위 댓글의 존재 유무
	int selectAnboardLev(AnboardVO vo)  throws Exception;

	// 수정처리 (삭제 시 하위 댓글이 존재하는 경우 - 내용을 비움)
	int updateAnboardDel(AnboardVO vo)  throws Exception;

	// 조회수 증가
	void updateAnboardHits(AnboardVO vo) throws Exception;

}





