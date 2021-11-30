package com.project.blogger.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follower")
public class Follower {

  @EmbeddedId
  private FollowerId id;

  @ManyToOne
  @MapsId("followerId")
  @JoinColumn(name = "follower_id")
  private User follower;

  @ManyToOne
  @MapsId("authorId")
  @JoinColumn(name = "author_id")
  private User author;

}
