package com.socialbe.main.services.User;

import com.socialbe.main.DTO.request.UserDTO;
import com.socialbe.main.repository.User.User;

import java.util.List;


public interface UserService {
    public User createUser(UserDTO user) throws Exception;
    public List<User> getUsers(Integer pageNumber,Integer limit);
    public User findUserById(Long userId) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public  String followUser(Long followingUserId, Long followedByUserId) throws Exception;
    public String updateUserData(Long userId, UserDTO userDTO) throws Exception;
    public String deleteUser(Long userId) throws Exception;
    public List<User> searchUser(String value) throws Exception;

}
