package com.example.UberReviewService.Controller;

import com.example.UberReviewService.DTO.CreateReviewDto;
import com.example.UberReviewService.DTO.ReviewResponseDto;
import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.adapters.CreateReviewResponseDto;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/review")
public class ReviewServiceController {

    private final ReviewService reviewService;

    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    private CreateReviewResponseDto reviewResponseDto;

    private ReviewServiceController(ReviewService reviewService,
                                    CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter,
                                    CreateReviewResponseDto reviewResponseDto) {
        this.reviewService=reviewService;
        this.createReviewDtoToReviewAdapter=createReviewDtoToReviewAdapter;
        this.reviewResponseDto= reviewResponseDto;
            }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto newReview) throws Exception {

        try {
            Review incomingReview =this.createReviewDtoToReviewAdapter.convertDto(newReview);
            if(incomingReview == null){
                log.error("Review cannot exist without booking");
                return new ResponseEntity<>("booking doesn't exist for the given id",
                        HttpStatus.BAD_REQUEST);
            }
            Review review = reviewService.publishReview(incomingReview);
            ReviewResponseDto responseDto= reviewResponseDto.createResponseDTO(Optional.ofNullable(review));
            log.info("Successfully added Review {}",responseDto);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }catch (Exception anyException){
            if(newReview.getContent()==null) {
                log.error("content must not be null for Review");
                return new ResponseEntity<>("content can't be null", HttpStatus.BAD_REQUEST);
            }
            return  new ResponseEntity<>(anyException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getReviewById/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
       try {
           Optional<Review> review= reviewService.findReviewById(reviewId);
           ReviewResponseDto responseDto = reviewResponseDto.createResponseDTO(review);
           return new ResponseEntity<>(responseDto, HttpStatus.OK);
       } catch (Exception anyException) {
           return new ResponseEntity<>(anyException.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews()
    {
        try {
            List<Review> reviews = reviewService.findAllReviews();
            List<ReviewResponseDto> reviewResponseDtos = reviewResponseDto.createResponseDto(reviews);
            return new ResponseEntity<>(reviewResponseDtos, HttpStatus.OK);
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
