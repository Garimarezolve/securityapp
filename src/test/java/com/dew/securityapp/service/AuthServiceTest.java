package com.dew.securityapp.service;

import com.dew.securityapp.dto.LoginDTO;
import com.dew.securityapp.dto.SignUpDto;
import com.dew.securityapp.entity.Role;
import com.dew.securityapp.entity.User;
import com.dew.securityapp.model.ERole;
import com.dew.securityapp.repository.RoleRepository;
import com.dew.securityapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;

    @Test
    public void register_new_user() {

        SignUpDto signUpDto = testSignUpDto();
        User user = testUser();
        Role roles = new Role();
        roles.setName(ERole.ROLE_ADMIN);

    }

    private static LoginDTO testLoginDTO() {
        return LoginDTO.builder().password("test@123").usernameOrEmail("testuser@gmail.com").build();
    }

    private static SignUpDto testSignUpDto() {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName("testUser");
        signUpDto.setUsername("testusername@test.com");
        signUpDto.setPassword("Test@123");
        signUpDto.setEmail("testusername@test.com");
        return signUpDto;
    }


    private static User testUser() {
        User user = new User();
        user.setId(1l);
        user.setUsername("testusername@test.com");
        user.setPassword("Test@123");
        user.setEmail("testusername@test.com");
        user.setName("testUser");
        return user;
    }
}
