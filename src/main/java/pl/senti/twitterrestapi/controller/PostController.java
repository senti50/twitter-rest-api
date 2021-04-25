package pl.senti.twitterrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Post> getPosts() throws IllegalAccessException {
        return postService.getPosts();
    }

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable Long postId
    ) throws IllegalAccessException {
        return postService.getSinglePost(postId);
    }

}
