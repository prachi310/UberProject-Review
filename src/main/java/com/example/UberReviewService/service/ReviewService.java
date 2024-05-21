package com.example.UberReviewService.service;

import com.example.UberReviewService.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {

    public Optional<Review> findReviewById(Long Id) throws Exception;

    public List<Review> findAllReviews() throws Exception;

    public boolean deleteReviewById(Long id) throws Exception;

    public Review publishReview(Review review) throws Exception;

    public Review UpdateReview(Long id , Review review) throws Exception;



}
