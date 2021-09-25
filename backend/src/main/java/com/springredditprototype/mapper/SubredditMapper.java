package com.springredditprototype.mapper;

import com.springredditprototype.dto.SubredditDto;
import com.springredditprototype.model.Post;
import com.springredditprototype.model.Subreddit;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
  @Mapping(
    target = "numberOfPosts",
    expression = "java(mapPosts(subreddit.getPosts()))"
  )
  SubredditDto mapSubredditToDto(Subreddit subreddit);

  default Integer mapPosts(List<Post> numberOfPosts) {
    return numberOfPosts.size();
  }

  @InheritInverseConfiguration
  @Mapping(target = "posts", ignore = true)
  Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
