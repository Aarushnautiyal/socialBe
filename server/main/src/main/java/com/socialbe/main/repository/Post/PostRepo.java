package com.socialbe.main.repository.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    @Query(value = "select p from posts p  where p.user_id=2",nativeQuery = true)
    List<Post> findAllPosts(@Param(value = "userId") Long userId);
}
