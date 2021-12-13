/**
 * 
 */
package egov.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.CommentVO;
import egov.service.ReviewService;
import egov.service.ReviewVO;

/**
 * @author ezen
 *
 */
@Service("reviewService")
public class ReviewImpl implements ReviewService {

	@Resource(name="reviewDAO")
	ReviewDAO reviewDAO;
	
	@Override
	public String insertReview(ReviewVO vo) throws Exception {
		return reviewDAO.insertReview(vo);
	}

	@Override
	public List<?> selectReviewList(ReviewVO vo) throws Exception {
		return reviewDAO.selectReviewList(vo);
	}

	@Override
	public ReviewVO selectReviewDetail(ReviewVO vo) throws Exception {
		return reviewDAO.selectReviewDetail(vo);
	}

	@Override
	public int updateReview(ReviewVO vo) throws Exception {
		return reviewDAO.updateReview(vo);
	}

	@Override
	public int deleteReview(ReviewVO vo) throws Exception {
		return reviewDAO.deleteReview(vo);
	}

	@Override
	public int selectReviewTotal(ReviewVO vo) throws Exception {
		return reviewDAO.selectReviewTotal(vo);
	}

	@Override
	public String insertComment(CommentVO vo) throws Exception {
		return reviewDAO.insertComment(vo);
	}

	@Override
	public List<?> selectCommList(ReviewVO vo) throws Exception {
		return reviewDAO.selectCommList(vo);
	}

	@Override
	public int selectCommentPass(CommentVO vo) throws Exception {
		return reviewDAO.selectCommentPass(vo);
	}

	@Override
	public int updateComment(CommentVO vo) throws Exception {
		return reviewDAO.updateComment(vo);
	}

	@Override
	public int deleteComment(CommentVO vo) throws Exception {
		return reviewDAO.deleteComment(vo);
	}

	@Override
	public int selectReviewPass(ReviewVO vo) throws Exception {
		return reviewDAO.selectReviewPass(vo);
	}

	@Override
	public int deleteCommentAll(int p_unq) throws Exception {
		return reviewDAO.deleteCommentAll(p_unq);
	}

}




