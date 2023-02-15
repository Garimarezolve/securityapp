package com.dew.securityapp.service;

import com.dew.securityapp.advice.TrackExecutionTime;
import com.dew.securityapp.constant.ApplicationConstant;
import com.dew.securityapp.dto.*;
import com.dew.securityapp.entity.Role;
import com.dew.securityapp.entity.User;
import com.dew.securityapp.exception.ServiceException;
import com.dew.securityapp.model.ERole;
import com.dew.securityapp.repository.RoleRepository;
import com.dew.securityapp.repository.UserRepository;
import com.dew.securityapp.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements  AuthenticationService{

    private  final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private  final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;


    @Override
    @TrackExecutionTime
    public ResponseDto registerUser(SignUpDto signUpDto) {

        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw  new ServiceException(ApplicationConstant.ERROR_STATUS_CODE,HttpStatus.OK,ApplicationConstant.USER_ALREADY_EXIST);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw  new ServiceException(ApplicationConstant.ERROR_STATUS_CODE,HttpStatus.OK,ApplicationConstant.USER_ALREADY_EXIST);
        }
        Role roles = roleRepository.findByName(ERole.ROLE_ADMIN).get();

        User user= userRepository.save(User.builder()
                .name(signUpDto.getName())
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .roles(Collections.singleton(roles))
                        .build());

        return new SuccessResponseDto(UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).username(user.getUsername()).build()
                       ,ApplicationConstant.CREATED_MSG,ApplicationConstant.HTTP_RESPONSE_CREATED_CODE);

    }

    @Override
    @TrackExecutionTime
    public ResponseDto userLogin(LoginDTO loginDTO) {
        User existingUser= userRepository.findByUsername(loginDTO.usernameOrEmail());
        if(existingUser == null){
            return new ErrorResponseDto(ApplicationConstant.NOT_FOUND,
                    ApplicationConstant.NOT_FOUND_MSG);
        }
        return new SuccessResponseDto(UserDto.builder()
                .id(existingUser.getId())
                .name(existingUser.getName())
                .email(existingUser.getEmail())
                .username(existingUser.getUsername()).build());
    }

}
