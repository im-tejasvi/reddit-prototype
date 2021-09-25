package com.springredditprototype.repository;

import com.springredditprototype.model.Post;
import com.springredditprototype.model.Subreddit;
import com.springredditprototype.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllBySubreddit(Subreddit subreddit);

  List<Post> findByUser(User user);
}
