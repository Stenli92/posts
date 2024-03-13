package com.example.posts.services.impl;


import com.example.posts.domain.entities.Post;
import com.example.posts.repository.PostRepository;
import com.example.posts.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(RestTemplate restTemplate, ModelMapper modelMapper, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }
    @Override
    public void populatePostToDb() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = this.restTemplate.getForObject(url, Post[].class);

        if (posts != null){
            List<Post> postList = Arrays.stream(posts).map(e -> this.modelMapper.map(e, Post.class)).toList();
            this.postRepository.saveAll(postList);
        }
    }
}
