package com.socialbe.main.services;

import com.socialbe.main.DTO.request.UserDTO;
import com.socialbe.main.repository.User;
import com.socialbe.main.repository.UserRepo;
import com.socialbe.main.util.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<?> createUser(UserDTO user) throws Exception {
        User newUser = new User();
        try {
            populateUser(user, newUser);
            log.info("my user is {}", newUser);
            User savedUser = userRepo.save(newUser);
            return ResponseBuilder.buildResponse(HttpStatus.CREATED, savedUser);
        } catch (Exception e) {
            return ResponseBuilder.buildError(HttpStatus.CONFLICT, e.toString());
        }
    }

    @Override
    public ResponseEntity<?> getUsers() {
        List<User> users = userRepo.findAll();
        return ResponseBuilder.buildResponse(HttpStatus.CREATED, users);
    }

    @Override
    public ResponseEntity<?> findUserById(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> findUserByEmail(String email) {
        return null;
    }

    @Override
    public ResponseEntity<?> followUser(Long followingUserId, Long followedByUserId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUserData(Long userId, UserDTO userDto) throws Exception {

        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isEmpty()) {
            throw new Exception("User does not exist with the user ID: " + userId);
        }

        User user = userOptional.get();

        populateUser(userDto, user);
        return ResponseBuilder.buildResponse(HttpStatus.OK, user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,"User Does not exists");
        }
        userRepo.delete(user.get());
        return ResponseBuilder.buildResponse(HttpStatus.OK,"Deletion Successful");
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
