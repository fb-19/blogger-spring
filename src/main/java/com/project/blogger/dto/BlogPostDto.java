package com.project.blogger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDto {

  private Long id;

  private String title;

  private String content;

  private UserDto author;

}
