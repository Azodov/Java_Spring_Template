package com.template.project.service;

import com.template.project.domain.User;

public interface UserService {

    User createUser(User user);

    Boolean checkUsername(String username);

    Boolean checkPassword(String password);
}
