package com.post.controller;

import com.post.dto.PostRequest;
import com.post.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.post.service.postService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class postController {

    private final postService postService;

    public postController(postService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> AllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable int id) {
        Optional<Post> post = postService.getPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Optional<Post>> createPost(@RequestBody PostRequest post) {
        Optional<Post> p = postService.save(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping(path="delete/{id}")
    public ResponseEntity<Integer> deletePost(@PathVariable int id) {
        int p = postService.delete(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
