package pl.senti.twitterrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.senti.twitterrestapi.model.Comment;
import pl.senti.twitterrestapi.model.Post;
import pl.senti.twitterrestapi.repository.CommentsRepository;
import pl.senti.twitterrestapi.repository.PostRepository;


import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
        //given
        Post newPost = new Post();
        newPost.setTitle("Test");
        newPost.setContent("Test Content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);
        //when
        long newPostId = newPost.getId();
        MvcResult mvcResult = mockMvc.perform(get("/posts/" + newPostId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        //then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPostId);
        assertThat(post.getTitle()).isEqualTo("Test");
        assertThat(post.getContent()).isEqualTo("Test Content");

    }

    @Test
    @Transactional
    void shouldGetPostsWithComments() throws Exception {
        //when
        MvcResult mvcResult = mockMvc.perform(get("/posts/comments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        //then
        Post[] posts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post[].class);
        assertThat(posts).isNotNull();
        assertThat(posts).hasSize(20);
        // default PAGE_SIZE = 20 in PostService
    }

    @Test
    @Transactional
    void shouldAddPost() throws Exception {
        //given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setCreated(LocalDateTime.now());
        post.setContent("Test Content");

        //when
        MvcResult mvcResult = mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(post)))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //then
        Post posts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(posts).isNotNull();
        assertThat(post.getId()).isEqualTo(post.getId());
        assertThat(post.getTitle()).isEqualTo("Test Post");
        assertThat(post.getContent()).isEqualTo("Test Content");
    }

    @Test
    @Transactional
    void shouldEditPost() throws Exception {
        //given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Test Content");
        postRepository.save(post);
        Comment comment = new Comment();
        comment.setPostId(post.getId());
        comment.setContent("Comment Test Post");
        comment.setCreated(LocalDateTime.now());
        commentsRepository.save(comment);
        //when
        post.setContent("Test Content Edit");
        post.setTitle("Test Post Edit");
        MvcResult mvcResult = mockMvc.perform(put("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(post)))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //then
        Post posts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(posts).isNotNull();
        assertThat(post.getId()).isEqualTo(post.getId());
        assertThat(post.getTitle()).isEqualTo("Test Post Edit");
        assertThat(post.getContent()).isEqualTo("Test Content Edit");
    }

    @Test
    void shouldDeletePost() throws Exception {
        //given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Test Content");
        post.setCreated(LocalDateTime.now());
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setPostId(post.getId());
        comment.setContent("Comment Test Post");
        comment.setCreated(LocalDateTime.now());
        commentsRepository.save(comment);
        //then
        mockMvc.perform(delete("/posts/"+post.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}