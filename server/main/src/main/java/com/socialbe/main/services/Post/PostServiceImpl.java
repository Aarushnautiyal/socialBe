package com.socialbe.main.services.Post;

import com.socialbe.main.DTO.request.PostDto;
import com.socialbe.main.repository.Post.Post;
import com.socialbe.main.repository.Post.PostRepo;
import com.socialbe.main.repository.User.User;
import com.socialbe.main.repository.User.UserRepo;
import com.socialbe.main.services.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    @Override
    public String createPost(PostDto post, Long userId) throws Exception {
        try{
            Post newPost = new Post();
            User user = userService.findUserById(userId);
            log.info("Post is being saved for the user{}",user);
            newPost.setCaption(post.getCaption());
            newPost.setImage(post.getImage());
            newPost.setVideo(post.getVideo());
            newPost.setUser(user);
            newPost.setCreatedAt(LocalDateTime.now());
            postRepo.save(newPost);
            return "Post has been successfully created";
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public String deletePost(Long postId,Long userId) throws Exception {
        Post myPost = getPost(postId);
        if(myPost.getUser().getId()!=userId){
            throw  new Exception("User not eligible to del this post");
        }
        postRepo.delete(myPost);
        return "post deleted Successfully";
    }

    private Post getPost(Long postId) throws Exception {
        Optional<Post> optionalPost = postRepo.findById(postId);
        if(optionalPost.isEmpty()){
            throw new Exception("Post does not Exists");
        }
        return optionalPost.get();
    }

    @Override
    public String likePost(Long postId, Long userId) throws Exception {
        Post myPost = getPost(postId);
        User user = userService.findUserById(userId);
        if(myPost.getLike().contains(user)) {
            log.info("Post is being disLiked by this user {}",user.getId());
            myPost.getLike().remove(user);
        }else {
            log.info("Post is being Liked by this user {}",user.getId());
            myPost.getLike().add(user);
        }
        log.info("post is being saved {}",postRepo.save(myPost));
        return "liked Successful";
    }

    @Override
    public List<Post> findPostById(Long userId) throws Exception {
        try{
            log.info("Finding posts for the user {}",userId);
            List<Post> posts= postRepo.findAllPosts(userId);
            if(posts.isEmpty()){
                throw new Exception("no Posts found for the User");
            }
            return posts;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String savePostForUser(Long postId, Long userId) throws Exception {
        Post myPost = getPost(postId);
        User user = userService.findUserById(userId);
        user.getPosts().add(myPost);
        userRepo.save(user);
        return "Post saved";
    }
}
