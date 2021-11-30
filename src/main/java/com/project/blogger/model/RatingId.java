package com.project.blogger.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RatingId implements Serializable {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "blog_post_id")
  private Long blogPostId;

}
