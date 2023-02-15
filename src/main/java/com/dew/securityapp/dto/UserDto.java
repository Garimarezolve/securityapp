package com.dew.securityapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private String username;
    private String email;
}
