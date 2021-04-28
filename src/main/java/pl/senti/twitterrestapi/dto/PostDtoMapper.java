package pl.senti.twitterrestapi.dto;

import pl.senti.twitterrestapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public  class PostDtoMapper {
    private PostDtoMapper(){}

    public static List<PostDto> mapToPostDto(List<Post> posts) {
        return posts.stream().
                map(PostDtoMapper::mapTODto)
                .collect(Collectors.toList());
    }

    public static PostDto mapTODto(Post post) {
        return new PostDto.Builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
