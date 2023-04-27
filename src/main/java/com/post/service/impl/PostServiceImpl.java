package com.post.service.impl;

import com.post.dto.PostRequest;
import com.post.entity.Post;
import com.post.entity.User;
import com.post.repository.PostRepository;
import com.post.repository.UserRepository;
import com.post.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post save(PostRequest postRequest) {
        User userD = new User();
        Optional<User> user = userRepository.findById(postRequest.getUser().getId());
        if(user.isPresent()) {
            userD.setId(user.get().getId());
        }

        Post post = new Post(postRequest.getTitle(), postRequest.getContent(), userD);
        Post p = postRepository.save(post);
        return p;
    }

    public int delete(int id) {
        Post post = this.getPost(id);
        if(post != null) {
            postRepository.deleteById(id);
            return id;
        }
        return 0;
    }

}