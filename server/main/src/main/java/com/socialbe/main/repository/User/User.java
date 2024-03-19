package com.socialbe.main.repository.User;

import com.socialbe.main.repository.Post.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "social_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "followers")
    private Set<Long> followers;

    @Column(name = "followings")
    private Set<Long> followings;

    @ManyToMany
    private List<Post> posts= new ArrayList<>();
}
