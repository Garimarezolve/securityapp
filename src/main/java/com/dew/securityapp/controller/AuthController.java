package com.dew.securityapp.controller;

import com.dew.securityapp.advice.TrackExecutionTime;
import com.dew.securityapp.dto.LoginDTO;
import com.dew.securityapp.dto.ResponseDto;
import com.dew.securityapp.dto.SignUpDto;
import com.dew.securityapp.repository.UserRepository;
import com.dew.securityapp.service.AuthenticationService;
import com.dew.securityapp.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${jwt.secret}")
    private String tokenHeader;
    @Value("${jwt.prefix}")
    private String tokenPrefix;


    @PostMapping("/signin")
    @TrackExecutionTime
    public ResponseDto authenticateUser(@RequestBody LoginDTO loginDto, HttpServletResponse response){
        log.info("authenticating User for request {}", loginDto);
        response.addHeader(tokenHeader, tokenPrefix + jwtTokenUtil.generateToken(loginDto.usernameOrEmail()));
        return authenticationService.userLogin(loginDto);
    }

    @PostMapping("/signup")
    @TrackExecutionTime
    public ResponseDto registerUser(@RequestBody SignUpDto signUpDto){
        log.info("Register New  User for request {}", signUpDto);
      return  authenticationService.registerUser(signUpDto);

    }

}
