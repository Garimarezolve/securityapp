package com.dew.securityapp.dto;

import lombok.Builder;


public record LoginDTO (String usernameOrEmail,String password){
    @Builder
    public LoginDTO {}
}


