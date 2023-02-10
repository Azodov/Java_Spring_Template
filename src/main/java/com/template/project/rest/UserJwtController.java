package com.template.project.rest;


import com.template.project.rest.VM.LoginVM;
import com.template.project.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserJwtController {

    private final UserServiceImpl userServiceImpl;

    public UserJwtController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginVM loginVM) {
        String token = userServiceImpl.login(loginVM);
        Header header = new Header("Token", token);
        return new ResponseEntity<>(header, null, HttpStatus.OK);
    }
}