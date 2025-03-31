package org.expense.test.Service;

import org.expense.test.entity.User;
import org.expense.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name, String pin) {
        User user = new User();
        user.setUserId(UUID.fromString(UUID.randomUUID().toString()));
        user.setName(name);
        user.setPin(pin);
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User verifyUser(String name, String pin) {
        return userRepository.findByNameAndPin(name, pin)
                .orElseThrow(() -> new RuntimeException("Invalid Name or PIN"));
    }
}

