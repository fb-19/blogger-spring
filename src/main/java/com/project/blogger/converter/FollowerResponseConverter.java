package com.project.blogger.converter;

import com.project.blogger.dto.FollowerResponseDto;
import com.project.blogger.model.Follower;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowerResponseConverter implements Converter<Follower, FollowerResponseDto> {

  private final UserConverter userConverter;

  @Override
  public FollowerResponseDto convert(final Follower follower) {
    return FollowerResponseDto.builder()
        .followerDto(userConverter.convert(follower.getFollower()))
        .authorDto(userConverter.convert(follower.getAuthor()))
        .build();
  }

}
