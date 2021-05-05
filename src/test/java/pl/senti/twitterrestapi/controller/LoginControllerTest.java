package pl.senti.twitterrestapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldLoginAndGetContent() throws Exception {
        MvcResult login= mockMvc.perform(post("/login")
                .content("{\"username\": \"test\",\"password\": \"test\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String token = login.getResponse().getHeader("Authorization");

        mockMvc.perform(get("/secured")
                .header("Authorization",token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("secured"));

    }
    @Test
    void shouldDontGetContentAndThrow401() throws Exception {
        mockMvc.perform(get("/secured"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}