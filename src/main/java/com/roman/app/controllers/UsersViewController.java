package com.roman.app.controllers;

import com.roman.app.model.User;
import com.roman.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.lang3.RandomUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@RestController
//@RequestMapping("/users") TODO: make it work wih RedirectView
public class UsersViewController {

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
    public ModelAndView deleteUser(User user) {
        long userId = user.getId();
        if (userRepository.exists(userId)) {
            userRepository.delete(userId);
            return new ModelAndView(new RedirectView("/showAll", true));
        } else {
            return new ModelAndView("errorMessage", "message", "There is no user with id = " + userId);
        }
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(NullPointerException ex) {
        return new ModelAndView("errorMessage", "message", "can not find user by id " + ex.getCause());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser() {
        return new ModelAndView("addUser", "form", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            return new ModelAndView("errorMessage", "message", "Please fill User name parameter");
        } else if (userRepository.findByName(user.getName()) != null) {
            return new ModelAndView("errorMessage", "message", "User with such name already exists in DB!");
        }
        userRepository.save(user);
        return new ModelAndView(
                new RedirectView("/showAll", true)
        );
    }

    @PostConstruct
    public void postConstruct() {
        userRepository.save(new User("Johnny", RandomUtils.nextInt(10, 70)));
        userRepository.save(new User("Tommy", RandomUtils.nextInt(10, 70)));
        userRepository.save(new User("Deedee", RandomUtils.nextInt(10, 70)));
    }

    @PreDestroy
    public void preDestroy() {
        userRepository.deleteAll();
    }
}