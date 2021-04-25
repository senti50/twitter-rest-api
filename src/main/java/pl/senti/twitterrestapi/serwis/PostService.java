package pl.senti.twitterrestapi.serwis;

import org.springframework.stereotype.Service;
import pl.senti.twitterrestapi.model.Post;
import pl.senti.twitterrestapi.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getSinglePost(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }
}
