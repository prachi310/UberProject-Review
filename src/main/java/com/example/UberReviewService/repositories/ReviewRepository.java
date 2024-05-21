package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

  Integer countAllByRatingIsLessThanEqual(Integer givenRating);

  List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);

  List<Review> findAllByCreatedAtBefore(Date date);

  @Query("SELECT r from Booking b INNER JOIN Review r WHERE b.id =:Id")
  Review reviewByBookingId(Long Id);


}
