package com.example.UberReviewService.service;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;

    private ReviewService(ReviewRepository reviewRepository)
    {
        this.reviewRepository=reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************");
        Review review=Review.builder()
                .content("Amazing ride experience")
                .ratings(5.0).build();
        System.out.println(review);
        reviewRepository.save(review);// this will execute sql query

    }
}
