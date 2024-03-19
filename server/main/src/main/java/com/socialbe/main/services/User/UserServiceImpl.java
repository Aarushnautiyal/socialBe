package com.socialbe.main.services.User;

import com.socialbe.main.DTO.request.UserDTO;
import com.socialbe.main.repository.User.User;
import com.socialbe.main.repository.User.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(UserDTO user) throws Exception {
        User newUser = new User();
        try {
            populateUser(user, newUser);
            log.info("my user is {}", newUser);
            return userRepo.save(newUser);
        } catch (Exception e) {
             throw new Exception("hey");
        }
    }

    @Override
    public List<User> getUsers(Integer pageNumber, Integer limit) {
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        Page<User> userPage= userRepo.findAll(pageRequest);
        return  userPage.getContent();
    }


    @Override
    public User findUserById(Long userId) throws Exception {
        try{
            Optional<User> user = userRepo.findById(userId);
            if(user.isPresent()){
                return user.get();
            }else {
                throw new Exception("user does not exist");
            }
        }catch (Exception err){
            throw new Exception(err);
        }
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        try{
            Optional<User> user = userRepo.findByEmail(email);
            if(user.isPresent()){
                return user.get();
            }else {
                throw new Exception("user does not exist");
            }
        }catch (Exception err){
            throw new Exception(err);
        }
    }

    @Override
    public String followUser(Long followingUserId, Long followedByUserId) throws Exception {
        User following = findUserById(followingUserId);
        User follower= findUserById(followedByUserId);
        if (follower.getFollowings() == null) {
            follower.setFollowings(new HashSet<>());
        }
        if (following.getFollowers() == null) {
            following.setFollowers(new HashSet<>());
        }
        follower.getFollowings().add(followingUserId);
        following.getFollowers().add(followedByUserId);
        userRepo.saveAll(Arrays.asList(follower, following));
        return "Followed Successfully";
    }

    @Override
    public String updateUserData(Long userId, UserDTO userDto) throws Exception {
        User user = findUserById(userId);
        populateUser(userDto, user);
        userRepo.save(user);
        return "User Value Updated";
    }

    @Override
    public String deleteUser(Long userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
           throw new Exception("User Does not exists");
        }
        userRepo.delete(user.get());
        return "Deletion Successful";
    }

    @Override
    public List<User> searchUser(String value) throws Exception {
        log.info("Entering the search service with val as {}",value);
    try{
        List<User> users = userRepo.search(value);
        if(users.isEmpty()){ throw new Exception("no user found");}
        return users;
    }catch(Exception e){
        throw new Exception(e.getMessage());
    }
    }

    private static void populateUser(UserDTO userDto, User user) {
        if (userDto.getFirstName() != null && !userDto.getFirstName().isEmpty()) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null && !userDto.getLastName().isEmpty()) {
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword());
        }
    }
}
