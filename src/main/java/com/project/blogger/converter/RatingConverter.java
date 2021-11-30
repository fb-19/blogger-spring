package com.project.blogger.converter;

import com.project.blogger.dto.RatingRequestDto;
import com.project.blogger.model.BlogPost;
import com.project.blogger.model.Rating;
import com.project.blogger.model.RatingId;
import com.project.blogger.model.User;
import com.project.blogger.repository.BlogPostRepository;
import com.project.blogger.repository.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingConverter implements Converter<RatingRequestDto, Rating> {

  private final UserRepository userRepository;

  private final BlogPostRepository blogPostRepository;

  @Override
  public Rating convert(final RatingRequestDto ratingRequestDto) {
    return Rating.builder()
        .id(RatingId.builder().userId(ratingRequestDto.getUserId()).blogPostId(ratingRequestDto.getBlogPostId()).build())
        .user(getUserById(ratingRequestDto.getUserId()))
        .blogPost(getBlogPostById(ratingRequestDto.getBlogPostId()))
        .numericRating(ratingRequestDto.getNumericRating())
        .build();
  }

  private User getUserById(final Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("User with ID: " + id + " not found."));
  }

  private BlogPost getBlogPostById(final Long id) {
    return blogPostRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Blog post with ID: " + id + " not found."));
  }
}