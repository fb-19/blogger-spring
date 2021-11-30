package com.project.blogger.converter;

import com.project.blogger.dto.BlogPostCreateDto;
import com.project.blogger.model.User;
import com.project.blogger.model.BlogPost;
import com.project.blogger.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostCreateConverter implements Converter<BlogPostCreateDto, BlogPost> {

  private final UserRepository userRepository;

  @Override
  public BlogPost convert(final BlogPostCreateDto source) {
    return BlogPost.builder()
        .title(source.getTitle())
        .content(source.getContent())
        .author(getAuthor(source.getAuthorId()))
        .timeCreated(LocalDateTime.now())
        .build();
  }

  private User getAuthor(final Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Author with ID: " + id + " not found."));
  }

}
