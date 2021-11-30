package com.project.blogger.service.impl;

import com.project.blogger.converter.FollowerConverter;
import com.project.blogger.converter.FollowerResponseConverter;
import com.project.blogger.dto.FollowerRequestDto;
import com.project.blogger.dto.FollowerResponseDto;
import com.project.blogger.model.Follower;
import com.project.blogger.model.FollowerId;
import com.project.blogger.repository.FollowerRepository;
import com.project.blogger.service.FollowerService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {

  private final FollowerRepository followerRepository;

  private final FollowerConverter followerConverter;

  private final FollowerResponseConverter followerResponseConverter;

  @Override
  public FollowerResponseDto follow(final FollowerRequestDto followerRequestDto) {
    Follower follower = followerConverter.convert(followerRequestDto);

    if (Objects.isNull(follower)) {
      throw new IllegalArgumentException("Empty follower cannot be saved.");
    }

    final var followerId = FollowerId.builder()
                .followerId(follower.getFollower().getId())
                .authorId(follower.getAuthor().getId())
                .build();
    follower.setId(followerId);

    Follower savedFollower = followerRepository.save(follower);

    return followerResponseConverter.convert(savedFollower);
  }

  @Override
  @Transactional
  public void unfollow(final Long followerId, final Long authorId) {
    followerRepository.deleteByFollowerIdAndAuthorId(followerId, authorId);
  }

  @Override
  public Boolean checkFollowerStatus(final Long followerId, final Long authorId) {
    if (Boolean.TRUE.equals(followerRepository.existsByFollowerIdAndAuthorId(followerId, authorId))) {
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

}
