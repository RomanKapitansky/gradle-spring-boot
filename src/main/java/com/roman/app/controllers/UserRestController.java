package com.roman.app.controllers;

import com.roman.app.exception.NoSuchUserException;
import com.roman.app.model.User;
import com.roman.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/rest")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/findUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> findUserById(@RequestParam(value="id") long id) {
        Map<String, String> response = new HashMap<>();
        try {
            User user = userRepository.findById(id);
            response.put("status", "success");
            response.put("userData", user.toString());
        } catch (Exception e) {
            response.put("status", "user has not been found");
        }
        return response;
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