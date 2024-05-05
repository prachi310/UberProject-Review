package com.example.UberReviewService.service;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReviewService implements CommandLineRunner {

    private DriverRepository driverRepository;
    private BookingRepository bookingRepository;

////    private ReviewRepository reviewRepository;
////
////    private ReviewService(ReviewRepository reviewRepository,
////                          BookingRepository bookingRepository)
////    {
////        this.reviewRepository=reviewRepository;
////        this.bookingRepository = bookingRepository;
////    }
////
////    @Override
////    public void run(String... args) throws Exception {
////        System.out.println("************");
////        Review review=Review.builder()
////                .content("Amazing ride experience")
////                .ratings(5.0).build();
////
////        Booking booking = Booking.builder()
////                         .endTime(new Date())
////                          .review(review)
////                          .build();
////        reviewRepository.save(review);// this will execute sql query
////        bookingRepository.save(booking);
////
////    }
//
    public ReviewService(DriverRepository driverRepository,BookingRepository bookingRepository) {
        this.driverRepository = driverRepository;
        this.bookingRepository=bookingRepository;
    }
    @Override
    @Transactional
    public void run(String... args) throws Exception {
//////        Optional<Driver> driver = driverRepository.findById(1L);
//////        if(driver.isPresent())
//////        {
//////           System.out.println(driver.get().getDriverName());
//////           List<Booking> b = driver.get().getBookings();
////////            List<Booking> bookings = bookingRepository.findAllByDriverId(1L);
//////            for(Booking booking:b)
//////            {
//////                System.out.println(booking.getId());
//////            }
//////
//////        }
////
//////        Optional<Driver> driver = driverRepository
//////        .rawFindByIdAndLicenceNumber(2L , "QWE12456");
//////        System.out.println(driver.get().getDriverName());
////
//////        Optional<Driver >driver = driverRepository.rawFindByIdAndLicence(2L,"QWE12456");
//////        System.out.println(driver.get().getDriverName());
////
        List<Long> driverIds = new ArrayList<>(Arrays.asList(1L, 2L,3L,4L,5L));

        List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);

        //List<Booking> bookings = bookingRepository.findAllByIdIn(driverIds);

        for(Driver driver: drivers)
        {
          List<Booking> bookings = driver.getBookings();
          bookings.forEach(booking -> System.out.println(booking.getId()));
        }


    }


}
