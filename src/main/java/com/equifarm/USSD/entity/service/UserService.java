package com.equifarm.USSD.entity.service;

import com.equifarm.USSD.entity.User;

import java.util.List;

public interface UserService {
    Object saveUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User updateUser(User user, long id);
    void deleteUser(long id);

}
