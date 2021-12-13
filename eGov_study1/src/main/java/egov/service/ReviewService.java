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

	/*
	 * 본문 암호 확인
	 */
	int selectReviewPass(ReviewVO vo) throws Exception;
	
	
	/*
	 * 코멘트 저장
	 */
	String insertComment(CommentVO vo) throws Exception;
	
	/*
	 * 코멘트 목록
	 */
	List<?> selectCommList(ReviewVO vo) throws Exception;

	/*
	 * 암호 비교
	 */
	int selectCommentPass(CommentVO vo) throws Exception;

	/*
	 * 코멘트 변경(수정) 처리
	 */
	int updateComment(CommentVO vo) throws Exception;

	/*
	 * 코멘트 삭제 처리
	 */
	int deleteComment(CommentVO vo) throws Exception;

	int deleteCommentAll(int p_unq) throws Exception;

	
}








