package com.socialbe.main.controller;

import com.socialbe.main.DTO.request.PostDto;
import com.socialbe.main.repository.Post.Post;
import com.socialbe.main.services.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/post")
public class PostController extends AbstractController {
    @Autowired
    PostService postService;

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPosts(@RequestBody PostDto post, @PathVariable Long userId) {
        try {
            String res = postService.createPost(post, userId);
            return buildResponse(HttpStatus.CREATED, res);
        } catch (Exception e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(value = "/{postId}/user/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            String res = postService.deletePost(postId, userId);
            return buildResponse(HttpStatus.OK, res);
        } catch (Exception e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(value = "like/{postId}/user/{userId}")
    public ResponseEntity<?> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            String res = postService.likePost(postId, userId);
            return buildResponse(HttpStatus.OK, res);
        } catch (Exception e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PutMapping(value = "/savePost/{postId}/user/{userId}")
    public ResponseEntity<?> savePosts(@PathVariable Long postId, @PathVariable Long userId) {
        try {
            String res = postService.savePostForUser(postId, userId);
            return buildResponse(HttpStatus.OK, res);
        } catch (Exception e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping(value = "/findPost/{userId}")
    public ResponseEntity<?> findPostByUserId(@PathVariable Long userId) {
        try {
            List<Post> res = postService.findPostById( userId);
            return buildResponse(HttpStatus.OK, res);
        } catch (Exception e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
