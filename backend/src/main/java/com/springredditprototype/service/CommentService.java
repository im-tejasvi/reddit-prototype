package com.springredditprototype.service;

import static java.util.stream.Collectors.toList;

import com.springredditprototype.dto.CommentsDto;
import com.springredditprototype.exceptions.PostNotFoundException;
import com.springredditprototype.mapper.CommentMapper;
import com.springredditprototype.model.Comment;
import com.springredditprototype.model.NotificationEmail;
import com.springredditprototype.model.Post;
import com.springredditprototype.model.User;
import com.springredditprototype.repository.CommentRepository;
import com.springredditprototype.repository.PostRepository;
import com.springredditprototype.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

  private static final String POST_URL = "";
  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final AuthService authService;
  private final CommentMapper commentMapper;
  private final CommentRepository commentRepository;
  private final MailContentBuilder mailContentBuilder;
  private final MailService mailService;

  public void save(CommentsDto commentsDto) {
    Post post = postRepository
      .findById(commentsDto.getPostId())
      .orElseThrow(
        () -> new PostNotFoundException(commentsDto.getPostId().toString())
      );
    Comment comment = commentMapper.map(
      commentsDto,
      post,
      authService.getCurrentUser()
    );
    commentRepository.save(comment);

    String message = mailContentBuilder.build(
      authService.getCurrentUser() +
      " posted a comment on your post." +
      POST_URL
    );
    sendCommentNotification(message, post.getUser());
  }

  private void sendCommentNotification(String message, User user) {
    mailService.sendMail(
      new NotificationEmail(
        user.getUsername() + " Commented on your post",
        user.getEmail(),
        message
      )
    );
  }

  public List<CommentsDto> getAllCommentsForPost(Long postId) {
    Post post = postRepository
      .findById(postId)
      .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    return commentRepository
      .findByPost(post)
      .stream()
      .map(commentMapper::mapToDto)
      .collect(toList());
  }

  public List<CommentsDto> getAllCommentsForUser(String userName) {
    User user = userRepository
      .findByUsername(userName)
      .orElseThrow(() -> new UsernameNotFoundException(userName));
    return commentRepository
      .findAllByUser(user)
      .stream()
      .map(commentMapper::mapToDto)
      .collect(toList());
  }
}
