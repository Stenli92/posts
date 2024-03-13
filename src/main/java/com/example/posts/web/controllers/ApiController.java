package com.example.posts.web.controllers;


import com.example.posts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final PostService postService;

    @Autowired
    public ApiController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String getPosts(){
        this.postService.populatePostToDb();

        return "/index.html";
    }
}
