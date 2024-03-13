package com.example.posts.services;

import com.example.posts.domain.entities.Post;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    List<Post> populatePostToDb();

}
