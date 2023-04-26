package com.post.service.impl;

import com.post.dto.PostRequest;
import com.post.model.Post;
import com.post.model.User;
import com.post.repository.postRepository;
import com.post.repository.userRepository;
import com.post.service.postService;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class postServiceImpl implements postService {

    private final postRepository postRepository;
    private final userRepository userRepository;

    public postServiceImpl(postRepository postRepository, userRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(int id) {
        return postRepository.findById(id);
    }

    public Optional<Post> save(PostRequest postRequest) {
        User userD = new User();
        Optional<User> user = userRepository.findById(postRequest.getUser());
        System.out.println(user.toString());
        if(user != null) {
            userD.setId(user.get().getId());
        }

        Post post = new Post(postRequest.getTitle(), postRequest.getContent(), userD);
        System.out.println(post.toString());
        Post p = postRepository.save(post);
        return Optional.of(p);
    }

    public int delete(int id) {
        Optional<Post> post = this.getPost(id);
        if(post != null) {
            postRepository.deleteById(id);
            return id;
        }
        return 0;

    }

}
