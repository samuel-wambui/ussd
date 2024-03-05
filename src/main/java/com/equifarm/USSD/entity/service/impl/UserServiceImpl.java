package com.equifarm.USSD.entity.service.impl;

import com.equifarm.USSD.ResourceNotFound;
import com.equifarm.USSD.entity.User;
import com.equifarm.USSD.entity.UserRepository;
import com.equifarm.USSD.entity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFound("Employee", "Id", id);
        }
        // return employeeRepository.findAllById(id).orElseThrow(()->new ExemptionNotFound("Employee", "Id", id))
    }

    @Override
    public User updateUser(User user, long id) {

       User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Employee", "Id", id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLocation(user.getLocation());
        existingUser.setLastName(user.getLastName());
        existingUser.setNationalId(user.getNationalId());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(long id) {
            userRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("Employee", "employee", id));
            userRepository.deleteById(id);

        }
    }








