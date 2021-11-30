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
public class FollowerId implements Serializable {

  @Column(name = "follower_id")
  private Long followerId;

  @Column(name = "author_id")
  private Long authorId;

}
