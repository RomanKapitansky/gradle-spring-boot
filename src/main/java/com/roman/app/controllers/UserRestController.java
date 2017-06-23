package com.roman.app.controllers;

import com.roman.app.exception.NoSuchUserException;
import com.roman.app.model.User;
import com.roman.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/findUserById")
    public User findUserById(@RequestParam(value="id") long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/updateUserById")
    public User updateUserById(
            @RequestParam(value="id") long id,
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age) throws NoSuchUserException {
        if (userRepository.exists(id)) {
            userRepository.updateById(id, name, age);
            return userRepository.findById(id);

        } else
            throw new NoSuchUserException();
    }
}