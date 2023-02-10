package com.template.project.rest;

import com.template.project.domain.User;
import com.template.project.repository.UserRepository;
import com.template.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.checkUsername(user.getUsername())) {
            return new ResponseEntity<>("Bu user oldin ro'yxatdan o'tgan", HttpStatus.BAD_REQUEST);
        }

        if (!userService.checkPassword(user.getPassword())) {
            return new ResponseEntity<>("Parol uzuligi 4 dan kam", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
