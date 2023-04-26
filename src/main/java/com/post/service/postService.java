package com.post.service;

import com.post.dto.PostRequest;
import com.post.model.Post;

import java.util.List;
import java.util.Optional;


public interface postService {

    public List<Post> getAllPosts();
    public Optional<Post> getPost(int id);
    public Optional<Post> save(PostRequest postRequest);
    public int delete(int id);

}
