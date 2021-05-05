package pl.senti.twitterrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.senti.twitterrestapi.dto.LoginCredentials;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials) {}

    @GetMapping("/secured")
    public String secured(){
        return "secured";
    }
}
