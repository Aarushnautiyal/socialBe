package com.socialbe.main.controller;

import com.socialbe.main.DTO.request.UserDTO;
import com.socialbe.main.services.UserService;
import com.socialbe.main.util.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> createUsers(@RequestBody UserDTO user) throws Exception {
           return  userService.createUser(user);
    }

    @PutMapping(value = "user/{userId}")
    public ResponseEntity<?> updateUserData(@PathVariable("userId")Long userId, @RequestBody UserDTO user) throws Exception {
        return userService.updateUserData(userId, user);
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<?> deleteUserID(@PathVariable("userId")Long userId) throws Exception {
        return userService.deleteUser(userId);
    }
}
