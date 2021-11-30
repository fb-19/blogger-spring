package com.project.blogger.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "blog_post")
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

  @Id
  @SequenceGenerator(name = "blog_post_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "blog_post_id_seq", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @ManyToOne
  private User author;

  @OneToMany(mappedBy = "blogPost")
  private List<Rating> ratings;

  @Column(name = "time_created")
  private LocalDateTime timeCreated;

  @Column(name = "time_updated")
  private LocalDateTime timeUpdated;

}
