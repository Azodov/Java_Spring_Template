package com.template.project.service.impl;

import com.template.project.domain.User;
import com.template.project.repository.UserRepository;
import com.template.project.rest.VM.LoginVM;
import com.template.project.security.JwtTokenProvider;
import com.template.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkPassword(String password) {
        return password.length() >= 4;
    }

    public String login(LoginVM loginVM) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword()));
            User user = userRepository.findByUsername(loginVM.getUsername());
            if (user == null) {
                throw new UsernameNotFoundException("Bu foydalanuvch mavjud emas");
            }
            return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        } catch (Exception e) {
            return "Login yoki parol xato bo'lishi mumkin\n";
        }
    }
}
