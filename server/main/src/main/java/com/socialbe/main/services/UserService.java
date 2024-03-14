package com.socialbe.main.services;

import com.socialbe.main.DTO.request.UserDTO;
import org.springframework.http.ResponseEntity;


public interface UserService {
    public ResponseEntity<?> createUser(UserDTO user) throws Exception;
    public ResponseEntity<?> getUsers();
    public ResponseEntity<?> findUserById(Long userId);
    public ResponseEntity<?> findUserByEmail(String email);
    public ResponseEntity<?> followUser(Long followingUserId, Long followedByUserId);
    public ResponseEntity<?> updateUserData(Long userId, UserDTO userDTO) throws Exception;
    public ResponseEntity<?> deleteUser(Long userId) throws Exception;
}
