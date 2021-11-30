package com.project.blogger.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostCreateDto {

  @NotNull
  private String title;

  @NotNull
  private String content;

  @NotNull
  private Long authorId;

}
