package com.post.service;

import com.post.dto.PostRequest;
import com.post.entity.Post;

import java.util.List;


public interface PostService {

    List<Post> getAllPosts();
    Post getPost(int id);
    Post save(PostRequest postRequest);
    int delete(int id);

}
