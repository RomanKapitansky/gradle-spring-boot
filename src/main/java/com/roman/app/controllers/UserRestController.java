package com.roman.app.controllers;

import com.roman.app.exception.NoSuchUserException;
import com.roman.app.model.User;
import com.roman.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/rest")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/findUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> findUserById(@RequestParam(value = "id") long id) {
        Map<String, String> response = new HashMap<>();
        User user = userRepository.findById(id);
        response.put("status", "success");
        response.put("userData", user.toString());
        return response;
    }

    @GetMapping("/updateUserById")
    public User updateUserById(
            @RequestParam(value="id") long id,
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value = "age") Integer age)  {
        if (userRepository.exists(id)) {
            userRepository.updateById(id, name, age);
            return userRepository.findById(id);

        } else
            throw new NoSuchUserException();
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<String> handle() {
        String responseBody = "User was not found";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}