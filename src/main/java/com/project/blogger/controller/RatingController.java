package com.project.blogger.controller;

import com.project.blogger.dto.AverageRatingDto;
import com.project.blogger.dto.RatingRequestDto;
import com.project.blogger.dto.RatingResponseDto;
import com.project.blogger.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rating")
public class RatingController {

  private final RatingService ratingService;

  @GetMapping("/average/{id}")
  public ResponseEntity<AverageRatingDto> calculateAverageByBlogPostId(@PathVariable final Long id) {
    return ResponseEntity.ok(ratingService.calculateAverageRatingByBlogPostId(id));
  }

  @GetMapping("/user-rating/{userId}/{blogPostId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<Double> getUserRatingByUserIdAndBlogPostId(@PathVariable final Long userId, @PathVariable final Long blogPostId) {
    return ResponseEntity.ok(ratingService.getUserRatingByUserIdAndBlogPostId(userId, blogPostId));
  }

  @PostMapping("/rate")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<RatingResponseDto> rate(@RequestBody final RatingRequestDto ratingRequestDto) {
    return ResponseEntity.ok(ratingService.rate(ratingRequestDto));
  }

}
