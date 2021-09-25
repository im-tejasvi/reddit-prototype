package com.springredditprototype.repository;

import com.springredditprototype.model.Post;
import com.springredditprototype.model.User;
import com.springredditprototype.model.Vote;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(
    Post post,
    User currentUser
  );
}
