package pl.senti.twitterrestapi.serwis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import pl.senti.twitterrestapi.model.Post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithMockUser
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void shouldGetSinglePost() {
        //given
        //when
        long postId = 1L;
        Post post = postService.getSinglePost(postId);
        //then
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(postId);

    }
}