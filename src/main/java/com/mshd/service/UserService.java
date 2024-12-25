package com.mshd.service;

import com.mshd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mshd.mapper.UserMapper;
import java.util.List;

@Service
public class UserService {

    private final UserMapper  userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean userExists(String username, String password) {
        String storedPassword = userMapper.getPassword(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public User getUserById(String username) {
       return userMapper.findByUsername(username);
    }

    public boolean addUser(String username, String password) {
        if(userMapper.insert(username, password)){
            return true;
        }else{
            return false;
        }

    }

    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    public int updateUsername(String id, String username) {
        return userMapper.updateUsername(id, username);
    }
}
