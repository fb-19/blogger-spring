package com.project.blogger.repository;

import com.project.blogger.model.Rating;
import com.project.blogger.model.RatingId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

  @Query(value = "SELECT avg(numeric_rating) FROM rating WHERE rating.blog_post_id = :id", nativeQuery = true)
  Double calculateAverageRatingByBlogPostId(@Param("id") Long id);

  Boolean existsByUserIdAndBlogPostId(Long userId, Long blogPostId);

  Optional<Rating> findByUserIdAndBlogPostId(Long userId, Long blogPostId);

}
