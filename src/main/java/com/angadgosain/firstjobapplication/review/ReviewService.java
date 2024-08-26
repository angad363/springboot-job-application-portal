package com.angadgosain.firstjobapplication.review;

import java.util.List;

public interface ReviewService {

    List<Review> findAll(Long companyId);

    boolean createReview(Review review, Long companyId);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview);

    boolean deleteReviewById(Long companyId, Long reviewId);
}
