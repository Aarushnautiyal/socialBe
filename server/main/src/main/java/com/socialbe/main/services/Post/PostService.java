package com.socialbe.main.services.Post;

import com.socialbe.main.DTO.request.PostDto;
import com.socialbe.main.repository.Post.Post;

import java.util.List;

public interface PostService {
    public String createPost(PostDto post, Long userId) throws Exception;

    public String deletePost(Long postId, Long userId) throws Exception;
    public String likePost(Long postId, Long userId) throws Exception;

    List<Post> findPostById(Long userId) throws Exception;

    public String savePostForUser(Long postId, Long userId) throws Exception;
}
