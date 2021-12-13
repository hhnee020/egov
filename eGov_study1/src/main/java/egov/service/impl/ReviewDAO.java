/**
 * 
 */
package egov.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egov.service.CommentVO;
import egov.service.ReviewVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @author ezen
 *
 */
@Repository("reviewDAO")
public class ReviewDAO  extends EgovAbstractDAO {

	public String insertReview(ReviewVO vo) {
		return (String) insert("reviewDAO.insertReview",vo);
	}

	public List<?> selectReviewList(ReviewVO vo) {
		return list("reviewDAO.selectReviewList",vo);
	}

	public ReviewVO selectReviewDetail(ReviewVO vo) {
		return (ReviewVO) select("reviewDAO.selectReviewDetail",vo);
	}

	public int updateReview(ReviewVO vo) {
		return update("reviewDAO.updateReview",vo);
	}

	public int deleteReview(ReviewVO vo) {
		return delete("reviewDAO.deleteReview",vo);
	}

	public int selectReviewTotal(ReviewVO vo) {
		return (int) select("reviewDAO.selectReviewTotal",vo);
	}

	public String insertComment(CommentVO vo) {
		return (String) insert("reviewDAO.insertComment",vo);
	}

	public List<?> selectCommList(ReviewVO vo) {
		return list("reviewDAO.selectCommList",vo);
	}

	public int selectCommentPass(CommentVO vo) {
		return (int) select("reviewDAO.selectCommentPass",vo);
	}

	public int updateComment(CommentVO vo) {
		return update("reviewDAO.updateComment",vo);
	}

	public int deleteComment(CommentVO vo) {
		return delete("reviewDAO.deleteComment",vo);
	}

	public int selectReviewPass(ReviewVO vo) {
		return (int) select("reviewDAO.selectReviewPass",vo);
	}

	public int deleteCommentAll(int p_unq) {
		return delete("reviewDAO.deleteCommentAll",p_unq);
	}

}






