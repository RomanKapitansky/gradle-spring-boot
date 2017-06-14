package com.roman.app.controllers;

import com.roman.app.repositories.UserRepository;
import com.roman.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@RestController
//@RequestMapping("/users") TODO: make it work wih RedirectView
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public ModelAndView showAll() {
        List<User> allUsers = userRepository.findAll();
        return new ModelAndView("allUsers", "users", allUsers);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser() {
        return new ModelAndView("deleteUser", "form", new User());
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ModelAndView deleteUser(User userId) {
        userRepository.delete(userId.getId());
        return new ModelAndView(
                new RedirectView("/showAll", true)
        );
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser() {
        return new ModelAndView("addUser", "form", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(User user) {
        userRepository.save(user);
        return new ModelAndView(
                new RedirectView("/showAll", true)
        );
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello from User Controller";
    }

    @PostConstruct
    public void postConstruct() {
        userRepository.save(new User("Ron"));
    }

    @PreDestroy
    public void preDestroy() {
        userRepository.deleteAll();
    }
}