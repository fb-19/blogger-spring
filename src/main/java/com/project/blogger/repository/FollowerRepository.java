package com.project.blogger.repository;

import com.project.blogger.model.Follower;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

  void deleteByFollowerIdAndAuthorId(Long followerId, Long authorId);

  List<Follower> findAllByFollowerId(Long id);

  Boolean existsByFollowerIdAndAuthorId(Long followerId, Long authorId);

}
