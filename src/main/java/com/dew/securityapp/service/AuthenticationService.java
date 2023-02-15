package com.dew.securityapp.service;

import com.dew.securityapp.dto.LoginDTO;
import com.dew.securityapp.dto.ResponseDto;
import com.dew.securityapp.dto.SignUpDto;

public interface AuthenticationService {
    ResponseDto registerUser(SignUpDto signUpDto);
    ResponseDto userLogin(LoginDTO loginDTO);
}
