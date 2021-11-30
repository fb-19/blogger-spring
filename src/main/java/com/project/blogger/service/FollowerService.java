package com.project.blogger.service;

import com.project.blogger.dto.FollowerRequestDto;
import com.project.blogger.dto.FollowerResponseDto;

public interface FollowerService {

  FollowerResponseDto follow(FollowerRequestDto followerRequestDto);

  void unfollow(Long followerId, Long authorId);

  Boolean checkFollowerStatus(Long followerId, Long authorId);

}
