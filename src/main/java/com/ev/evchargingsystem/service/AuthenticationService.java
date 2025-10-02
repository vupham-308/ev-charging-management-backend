package com.ev.evchargingsystem.service;

import com.ev.evchargingsystem.entity.User;
import com.ev.evchargingsystem.model.response.LoginRequest;
import com.ev.evchargingsystem.model.response.UserResponse;
import com.ev.evchargingsystem.repository.AuthenticationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    ModelMapper modelMapper;//map dữ liệu

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole()==null){
            user.setRole("USER");//mặc định role USER
        }
        return authenticationRepository.save(user);
    }

    public UserResponse login(LoginRequest loginRequest) {
        //xử lý logic đăng nhập
        //xác thực người dùng
        Authentication authentication;
        authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        User user= (User) authentication.getPrincipal();
        // User -> UserResponse
        UserResponse userResponse= modelMapper.map(user, UserResponse.class);
        String token= tokenService.generateToken(user);
        userResponse.setToken(token);
        return userResponse;
    }

}
