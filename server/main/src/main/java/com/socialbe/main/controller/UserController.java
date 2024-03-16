package com.socialbe.main.controller;

import com.socialbe.main.DTO.request.UserDTO;
import com.socialbe.main.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(){
        return buildResponse(HttpStatus.OK,userService.getUsers());
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> createUsers(@RequestBody UserDTO user) throws Exception {
           return  buildResponse(HttpStatus.CREATED,userService.createUser(user));
    }

    @PutMapping(value = "user/{userId}")
    public ResponseEntity<?> updateUserData(@PathVariable("userId")Long userId, @RequestBody UserDTO user) throws Exception {
        return buildResponse(HttpStatus.OK,userService.updateUserData(userId, user));
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<?> deleteUserID(@PathVariable("userId")Long userId) throws Exception {
        return buildResponse(HttpStatus.OK,userService.deleteUser(userId));
    }

    @GetMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByID(@PathVariable("userId") Long userId) throws Exception {
        return buildResponse(HttpStatus.OK,userService.findUserById(userId));
    }
    @GetMapping(value = "/usermail/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByEmail(@PathVariable("email")String email) throws Exception{
        return buildResponse(HttpStatus.OK,userService.findUserByEmail(email));
    }


    @PostMapping(value = "/user/{followerId}/following/{followingId}")
    public ResponseEntity<?> createUsers(@PathVariable Long followerId,@PathVariable Long followingId) throws Exception {
        return  buildResponse(HttpStatus.CREATED,userService.followUser(followingId,followerId));
    }


    @GetMapping(value = "/user/search")
    public ResponseEntity<?> createUsers(@RequestParam("user")String val) throws Exception {
        try{
            return buildResponse(HttpStatus.OK, userService.searchUser(val));
        }catch (Exception e){
           return buildError(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}
