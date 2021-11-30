package com.project.blogger.converter;

import com.project.blogger.dto.FollowerResponseDto;
import com.project.blogger.dto.RatingResponseDto;
import com.project.blogger.model.Follower;
import com.project.blogger.model.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingResponseConverter implements Converter<Rating, RatingResponseDto> {

  private final UserConverter userConverter;

  private final BlogPostConverter blogPostConverter;

  @Override
  public RatingResponseDto convert(final Rating rating) {
    return RatingResponseDto.builder()
        .userId(rating.getUser().getId())
        .blogPostId(rating.getBlogPost().getId())
        .numericRating(rating.getNumericRating())
        .build();
  }

}
