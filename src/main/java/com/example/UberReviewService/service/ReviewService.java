package com.example.UberReviewService.service;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;

    private ReviewService(ReviewRepository reviewRepository,
                          BookingRepository bookingRepository)
    {
        this.reviewRepository=reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************");
        Review review=Review.builder()
                .content("Amazing ride experience")
                .ratings(5.0).build();

        Booking booking = Booking.builder()
                         .endTime(new Date())
                          .review(review)
                          .build();
        reviewRepository.save(review);// this will execute sql query
        bookingRepository.save(booking);

    }
}
