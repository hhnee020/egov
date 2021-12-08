package egov.service;

import java.util.List;

public interface ReviewService {

	/*
	 * 저장처리
	 */
	String insertReview(ReviewVO vo) throws Exception;
	
	/*
	 * 목록출력
	 */
	List<?> selectReviewList(ReviewVO vo) throws Exception;
	
	/*
	 * 상세보기
	 */
	ReviewVO selectReviewDetail(ReviewVO vo) throws Exception;
	
	/*
	 * 수정처리
	 */
	int updateReview(ReviewVO vo) throws Exception;
	
	/*
	 * 삭제처리
	 */
	int deleteReview(ReviewVO vo) throws Exception;

	/*
	 * 총 데이터 개수
	 */
	int selectReviewTotal(ReviewVO vo) throws Exception;
}








