package com.socialbe.main.repository.Post;

import com.socialbe.main.repository.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="caption")
    private String Caption;

    @Column(name="image")
    private String Image;

    @Column(name="video")
    private String Video;

    @ManyToOne
    private User user;

    @OneToMany
    private List<User> Like = new ArrayList<>();

    @Column(name="created_at")
    private LocalDateTime createdAt;

}
