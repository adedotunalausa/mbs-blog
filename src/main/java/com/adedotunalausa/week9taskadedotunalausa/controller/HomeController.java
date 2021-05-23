package com.adedotunalausa.week9taskadedotunalausa.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/home")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeController {

    @GetMapping("/posts")
    public String allPosts() {
        return "User Posts.";
    }
}
