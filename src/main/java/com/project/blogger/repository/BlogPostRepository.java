package com.project.blogger.repository;

import com.project.blogger.model.BlogPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

  List<BlogPost> findAllByAuthorIdIn(List<Long> ids);

}
