package com.example.UberReviewService.adapters;

import com.example.UberReviewService.DTO.CreateReviewDto;
import com.example.UberReviewService.models.Review;

public interface CreateReviewDtoToReviewAdapter {

    public Review convertDto(CreateReviewDto createReviewDto);
}
