package com.project.blogger.converter;

import com.project.blogger.dto.FollowerRequestDto;
import com.project.blogger.model.Follower;
import com.project.blogger.model.User;
import com.project.blogger.repository.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowerConverter implements Converter<FollowerRequestDto, Follower> {

  private final UserRepository userRepository;

  @Override
  public Follower convert(final FollowerRequestDto followerRequestDto) {
    return Follower.builder()
        .follower(getUserById(followerRequestDto.getFollowerId()))
        .author(getUserById(followerRequestDto.getAuthorId()))
        .build();
  }

  private User getUserById(final Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("User with ID: " + id + " not found."));
  }

}
