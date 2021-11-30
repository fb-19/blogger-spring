package com.project.blogger.service.impl;

import com.project.blogger.converter.RatingConverter;
import com.project.blogger.converter.RatingResponseConverter;
import com.project.blogger.dto.AverageRatingDto;
import com.project.blogger.dto.RatingRequestDto;
import com.project.blogger.dto.RatingResponseDto;
import com.project.blogger.repository.RatingRepository;
import com.project.blogger.service.RatingService;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;

  private final RatingConverter ratingConverter;

  private final RatingResponseConverter ratingResponseConverter;

  @Override
  public RatingResponseDto rate(final RatingRequestDto ratingRequestDto) {
    if (Boolean.TRUE.equals(ratingRepository.existsByUserIdAndBlogPostId(ratingRequestDto.getUserId(), ratingRequestDto.getBlogPostId()))) {
      final var rating = ratingRepository.findByUserIdAndBlogPostId(ratingRequestDto.getUserId(), ratingRequestDto.getBlogPostId())
          .orElseThrow(() -> new NoSuchElementException("Rating not found."));

      rating.setNumericRating(ratingRequestDto.getNumericRating());

      ratingRepository.save(rating);

      return ratingResponseConverter.convert(rating);
    } else {
      final var rating = ratingConverter.convert(ratingRequestDto);

      if (Objects.isNull(rating)) {
        throw new IllegalArgumentException("Empty rating cannot be saved.");
      }

      final var newRating = ratingRepository.save(rating);

      return ratingResponseConverter.convert(newRating);
    }
  }

  @Override
  public AverageRatingDto calculateAverageRatingByBlogPostId(final Long id) {
    final var averageRating = ratingRepository.calculateAverageRatingByBlogPostId(id);

    return AverageRatingDto.builder()
        .average(averageRating)
        .build();
  }

  @Override
  public Double getUserRatingByUserIdAndBlogPostId(final Long userId, final Long blogPostId) {
    final var rating = ratingRepository.findByUserIdAndBlogPostId(userId, blogPostId);

    if (rating.isPresent()) {
      return rating.get().getNumericRating();
    } else {
      return 0.0;
    }
  }

}
