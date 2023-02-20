package com.dew.securityapp.service;

import com.dew.securityapp.dto.LoginDTO;
import com.dew.securityapp.dto.ResponseDto;
import com.dew.securityapp.dto.SignUpDto;
import com.dew.securityapp.entity.User;

public interface AuthenticationService {
    ResponseDto registerUser(SignUpDto signUpDto);
    ResponseDto userLogin(LoginDTO loginDTO);
    User fetchUserByEmail(String email);
}
