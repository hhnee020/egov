package egov.service.Impl;

/**
 * 
 */


import java.util.List;

import org.springframework.stereotype.Repository;

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

}

