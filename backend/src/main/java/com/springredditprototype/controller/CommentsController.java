package com.springredditprototype.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.springredditprototype.dto.CommentsDto;
import com.springredditprototype.service.CommentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<Void> createComment(
    @RequestBody CommentsDto commentsDto
  ) {
    commentService.save(commentsDto);
    return new ResponseEntity<>(CREATED);
  }

  @GetMapping("/by-post/{postId}")
  public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(
    @PathVariable Long postId
  ) {
    return ResponseEntity
      .status(OK)
      .body(commentService.getAllCommentsForPost(postId));
  }

  @GetMapping("/by-user/{userName}")
  public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(
    @PathVariable String userName
  ) {
    return ResponseEntity
      .status(OK)
      .body(commentService.getAllCommentsForUser(userName));
  }
}
