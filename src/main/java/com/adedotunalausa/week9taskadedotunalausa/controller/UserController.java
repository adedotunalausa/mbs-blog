package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

//    @PostMapping("/signup")
//    public User signUp(@Valid @RequestBody User newUser) {
//        return userService.createUser(newUser);
//    }
//
//    @GetMapping("/login")
//    public User login(@RequestBody User returningUser) {
//        return userService.getUser(returningUser.getEmail(), returningUser.getPassword());
//    }


}
