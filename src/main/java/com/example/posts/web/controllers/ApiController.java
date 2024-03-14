package com.example.posts.web.controllers;


import com.example.posts.domain.entities.Post;
import com.example.posts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.util.List;

@RestController
public class ApiController {

    private final PostService postService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    public ApiController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(Model model){
        // Create a Thymeleaf Context and add attributes to it
        Context context = new Context();

        // Process the template with the Thymeleaf Context
        String htmlContent = this.templateEngine.process("index", context);

        return htmlContent;
    }

    @GetMapping("/posts")
    public String getPosts(){
        List<Post> posts = this.postService.populatePostToDb();

        // Create a Thymeleaf Context and add attributes to it
        Context context = new Context();

        context.setVariable("posts" , posts);

        // Process the template with the Thymeleaf Context
        String htmlContent = this.templateEngine.process("posts", context);

        return htmlContent;

    }
}
