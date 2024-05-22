package com.example.UberReviewService.Controller;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewServiceController {

    private final ReviewService reviewService;

    private ReviewServiceController(ReviewService reviewService) {
        this.reviewService=reviewService;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody Review newReview) throws Exception {

        try {
            Review review = reviewService.publishReview(newReview);
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        }catch (Exception anyException){
            if(newReview.getContent()==null)
                return new ResponseEntity<>("content can't be null", HttpStatus.BAD_REQUEST);
            return  new ResponseEntity<>(anyException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getReviewById/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
       try {
           Optional<Review> review= reviewService.findReviewById(reviewId);
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

    @DeleteMapping("/deleteReviewById/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId)
    {
       try{
           boolean isDeleted = reviewService.deleteReviewById(reviewId);
           if (isDeleted)
               return  new ResponseEntity<>("deleted Review with id " +reviewId ,HttpStatus.OK);
           else
               return  new ResponseEntity<>("unable to delete review", HttpStatus.INTERNAL_SERVER_ERROR);
       }catch (Exception anyException)
       {
           return new ResponseEntity<>(anyException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @PutMapping("/updateReviewById/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review newReview)
    {
        try{
            Review review = reviewService.updateReview(reviewId, newReview);
            return new ResponseEntity<>("updated Review for Id:" +reviewId, HttpStatus.OK);
        }catch (Exception anyException)
        {
            return new ResponseEntity<>(anyException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
