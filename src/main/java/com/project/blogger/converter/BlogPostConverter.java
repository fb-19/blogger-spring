package com.project.blogger.converter;

import com.project.blogger.dto.BlogPostDto;
import com.project.blogger.model.BlogPost;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostConverter implements Converter<BlogPost, BlogPostDto> {

  private final UserConverter userConverter;

  @Override
  public BlogPostDto convert(final BlogPost blogPost) {
    return BlogPostDto.builder()
        .id(blogPost.getId())
        .title(blogPost.getTitle())
        .content(blogPost.getContent())
        .author(userConverter.convert(blogPost.getAuthor()))
        .build();
  }

}
