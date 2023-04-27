package com.post.controller;

import com.post.dto.PostRequest;
import com.post.entity.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.post.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> AllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id) {
        Post post = postService.getPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest post) {
        Post p = postService.save(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping(path="delete/{id}")
    public ResponseEntity<Integer> deletePost(@PathVariable int id) {
        int p = postService.delete(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
