package com.project.blogger.service;

import com.project.blogger.dto.AverageRatingDto;
import com.project.blogger.dto.RatingRequestDto;
import com.project.blogger.dto.RatingResponseDto;

public interface RatingService {

  AverageRatingDto calculateAverageRatingByBlogPostId(Long id);

  RatingResponseDto rate(RatingRequestDto ratingRequestDto);

  Double getUserRatingByUserIdAndBlogPostId(Long userId, Long blogPostId);

}
