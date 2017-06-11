package com.roman.app.controllers;

import com.roman.app.repositories.UserRepository;
import com.roman.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public ModelAndView showAll() {
        List<User> allUsers = new ArrayList<>();//userRepository.findAll();

        User user1 = new User("Name");
        user1.setId(1L);

        allUsers.add(user1);
        return new ModelAndView("allUsers", "users", allUsers);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello from User Controller";
    }

    @PostConstruct
    public void postConstruct() {
        userRepository.save(new User("Ron"));
    }
}