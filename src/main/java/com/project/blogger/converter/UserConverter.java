package com.project.blogger.converter;

import com.project.blogger.dto.UserDto;
import com.project.blogger.model.User;
import com.project.blogger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<User, UserDto> {

  private final UserRepository userRepository;

  @Override
  public UserDto convert(final User author) {
    return UserDto.builder()
        .id(author.getId())
        .username(author.getUsername())
        .build();
  }

}
