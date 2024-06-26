package com.example.UberReviewService.service;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService{

    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository =reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long Id) throws Exception {
        Optional<Review> review;
        try {
            review = reviewRepository.findById(Id);
            if (review.isEmpty())
                throw new EntityNotFoundException("Review with id " + Id + "not found");
        }catch(Exception anyException){
            anyException.printStackTrace();
            throw new Exception("unable to fetch the record"+ anyException.getMessage());
        }
        return review;
    }

    @Override
    public List<Review> findAllReviews() throws Exception {
        List<Review> reviews;
        try {
            reviews = reviewRepository.findAll();
        }catch (Exception anyException) {
            throw  new Exception("Exception occured while getting reviews : "
                    + anyException.getMessage());
        }
        return reviews;
    }

    @Override
    public boolean deleteReviewById(Long id) throws Exception {

        try {
            Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            this.reviewRepository.delete(review);
            return true;
        } catch (Exception anyException) {
            throw new Exception("unable to delete review :" +anyException.getMessage());
        }
    }

    @Override
    public Review publishReview(Review review) throws Exception {
        try{
            log.info("adding Review with Review Id : {}", review.getId());
            return reviewRepository.save(review);
        }catch (Exception anyException)
        {
            log.error("Exception while saving Review : " +anyException.getMessage());
            throw new Exception("Exception while publishing the Review :"
                    + anyException.getMessage());
        }
    }

    @Override
    public Review updateReview(Long id, Review newReviewData) throws Exception {
        try {
            Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Double Rating = newReviewData.getRating();
            if (Rating != null) {
                review.setRating(newReviewData.getRating());
            }
            if (newReviewData.getContent() != null) {
                review.setContent(newReviewData.getContent());
            }
            return this.reviewRepository.save(review);
        }catch (Exception anyException){
            throw new Exception("Exception while updating the Review :"
                    + anyException.getMessage());
        }
    }
}
