package com.springredditprototype.repository;

import com.springredditprototype.model.VerificationToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository
  extends JpaRepository<VerificationToken, Long> {
  Optional<VerificationToken> findByToken(String token);
}
