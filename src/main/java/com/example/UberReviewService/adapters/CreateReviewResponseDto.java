package com.example.UberReviewService.adapters;

import com.example.UberReviewService.DTO.ReviewResponseDto;
import com.example.UberReviewService.models.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CreateReviewResponseDto {

    public ReviewResponseDto createResponseDTO(Optional<Review> review)
    {
        return review.map(value -> ReviewResponseDto.builder()
                .rating(value.getRating())
                .content(value.getContent())
                .id(value.getId()).
                booking(value.getBooking().getId())
                .createdAt(value.getCreatedAt())
                .updatedAt(value.getUpdatedAt())
                .build()).orElse(null);
    }

    public List<ReviewResponseDto> createResponseDto(List<Review>reviews)
    {
        List<ReviewResponseDto> reviewList =new ArrayList<>();
        for(Review review :reviews)
        {
           reviewList.add(createResponseDTO(Optional.ofNullable(review)));
        }
        return reviewList;
    }
}
