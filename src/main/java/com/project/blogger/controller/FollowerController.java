package com.project.blogger.controller;

import com.project.blogger.dto.FollowerRequestDto;
import com.project.blogger.dto.FollowerResponseDto;
import com.project.blogger.model.security.response.MessageResponse;
import com.project.blogger.service.FollowerService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follower")
public class FollowerController {

  private final FollowerService followerService;

  @PostMapping("/follow")
  public ResponseEntity<FollowerResponseDto> follow(@Valid @RequestBody final FollowerRequestDto followerRequestDto) {
    return ResponseEntity.ok(followerService.follow(followerRequestDto));
  }

  @DeleteMapping("/unfollow/{followerId}/{authorId}")
  public ResponseEntity<MessageResponse> unfollow(@PathVariable final Long followerId, @PathVariable final Long authorId) {
    followerService.unfollow(followerId, authorId);
    return ResponseEntity.ok().body(new MessageResponse("Successfully unfollowed."));
  }

  @GetMapping("/status/{followerId}/{authorId}")
  public ResponseEntity<Boolean> checkFollowerStatus(@PathVariable final Long followerId, @PathVariable final Long authorId) {
    return ResponseEntity.ok(followerService.checkFollowerStatus(followerId, authorId));
  }

}
