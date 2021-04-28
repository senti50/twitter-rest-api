package pl.senti.twitterrestapi.controller;


import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.senti.twitterrestapi.dto.PostDto;
import pl.senti.twitterrestapi.dto.PostDtoMapper;
import pl.senti.twitterrestapi.model.Post;
import pl.senti.twitterrestapi.serwis.PostService;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort!= null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDto(postService.getPosts(pageNumber, sortDirection));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort!= null ? sort : Sort.Direction.ASC;
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }


    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable Long postId
    ) {
        return postService.getSinglePost(postId);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);

    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post){
        return postService.editPost(post);

    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id){
       postService.deletePost(id);
    }

}
