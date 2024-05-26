package com.example.UberReviewService.adapters;

import com.example.UberReviewService.DTO.CreateReviewDto;
import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter {

    private BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {

        Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());
        try {
            log.info("creating Review for Booking with id : {}", createReviewDto.getBookingId());
            return Review.builder().
                    content(createReviewDto.getContent())
                    .rating(createReviewDto.getRating())
                    .booking(booking.get())
                    .build();
        } catch (Exception anyException) {
            if(booking.isEmpty())
                return null;
            throw new RuntimeException("exception while creating review : " +
                    anyException.getMessage());}

        }
    }
}
