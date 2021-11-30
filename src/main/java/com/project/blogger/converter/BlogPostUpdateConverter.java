package com.project.blogger.converter;

import com.project.blogger.dto.BlogPostUpdateDto;
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
public class BlogPostUpdateConverter implements Converter<BlogPostUpdateDto, BlogPost> {

  private final UserRepository userRepository;

  @Override
  public BlogPost convert(final BlogPostUpdateDto source) {
    return BlogPost.builder()
        .title(source.getTitle())
        .content(source.getContent())
        .timeUpdated(LocalDateTime.now())
        .build();
  }

  private User getAuthor(final Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Author with ID: " + id + " not found."));
  }

}
