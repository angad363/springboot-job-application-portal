package com.angadgosain.firstjobapplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.findAll(companyId), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if(review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reviews/create")
    public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable Long companyId){
        boolean isCreated = reviewService.createReview(review, companyId);
        if(isCreated){
            return new ResponseEntity<>("Review created for the company", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found, review not created", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}/update")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId,
                                                   @PathVariable Long reviewId,
                                                   @RequestBody Review updatedReview){
        boolean isUpdated = reviewService.updateReviewById(companyId, reviewId, updatedReview);
        if(isUpdated){
            return new ResponseEntity<>("Review Updated successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/reviews/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }



}
