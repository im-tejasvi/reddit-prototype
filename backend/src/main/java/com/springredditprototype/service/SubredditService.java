package com.springredditprototype.service;

import static java.util.stream.Collectors.toList;

import com.springredditprototype.dto.SubredditDto;
import com.springredditprototype.exceptions.SpringRedditException;
import com.springredditprototype.mapper.SubredditMapper;
import com.springredditprototype.model.Subreddit;
import com.springredditprototype.repository.SubredditRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

  private final SubredditRepository subredditRepository;
  private final SubredditMapper subredditMapper;

  @Transactional
  public SubredditDto save(SubredditDto subredditDto) {
    Subreddit save = subredditRepository.save(
      subredditMapper.mapDtoToSubreddit(subredditDto)
    );
    subredditDto.setId(save.getId());
    return subredditDto;
  }

  @Transactional(readOnly = true)
  public List<SubredditDto> getAll() {
    return subredditRepository
      .findAll()
      .stream()
      .map(subredditMapper::mapSubredditToDto)
      .collect(toList());
  }

  public SubredditDto getSubreddit(Long id) {
    Subreddit subreddit = subredditRepository
      .findById(id)
      .orElseThrow(
        () -> new SpringRedditException("No subreddit found with ID - " + id)
      );
    return subredditMapper.mapSubredditToDto(subreddit);
  }
}
