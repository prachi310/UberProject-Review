package com.example.UberReviewService.Controller;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewServiceController {

    private ReviewService reviewService;

    private ReviewServiceController(ReviewService reviewService) {
        this.reviewService=reviewService;
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long id)
    {
       try {
           Optional<Review> review= reviewService.findReviewById(id);
           return new ResponseEntity<>(review, HttpStatus.OK);
       } catch (Exception anyException) {
           return new ResponseEntity<>(anyException.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews()
    {
        try {
            List<Review> reviews = reviewService.findAllReviews();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception anyException) {
            return new ResponseEntity<>(anyException.getMessage(),HttpStatus.NO_CONTENT);
        }
    }




}
