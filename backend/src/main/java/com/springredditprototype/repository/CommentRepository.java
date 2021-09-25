package com.springredditprototype.repository;

import com.springredditprototype.model.Comment;
import com.springredditprototype.model.Post;
import com.springredditprototype.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByPost(Post post);

  List<Comment> findAllByUser(User user);
}
