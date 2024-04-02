package com.nurlantr.springbootlibrary.service;

import com.nurlantr.springbootlibrary.dao.BookRepository;
import com.nurlantr.springbootlibrary.dao.ReviewRepository;
import com.nurlantr.springbootlibrary.entity.Review;
import com.nurlantr.springbootlibrary.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
        if (validateReview != null) {
            throw new Exception("Review already created");
        }
        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);

        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
//        System.out.println("Book id: " + review.getBookId());
//        System.out.println("Rating: " + review.getRating());
//        System.out.println("User email: " + review.getUserEmail());
//        System.out.println("Review Descr: " + review.getReviewDescription());
//        System.out.println("Date: " + review.getDate());
        reviewRepository.save(review);
    }


    public Boolean userReviewListed(String userEmail, Long bookId) {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateReview != null) {
            return true;
        }
        return false;
    }
}
