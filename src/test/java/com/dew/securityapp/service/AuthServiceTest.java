package com.dew.securityapp.service;

import com.dew.securityapp.dto.*;
import com.dew.securityapp.entity.User;
import com.dew.securityapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @Mock
    UserRepository userRepository;

    @Test
    void should_register_new_user(){
        User registerUser= testUser();
        when((userRepository.findByEmail(Mockito.any()))).thenReturn(registerUser);
        User user= authenticationService.fetchUserByEmail(registerUser.getEmail());
        assertThat(user).isEqualTo(user);
    }

    @Test
    void should_login_existing_user(){
        User registerUser= testUser();
        when((userRepository.findByUsername(Mockito.any()))).thenReturn(registerUser);
         ResponseDto response=  authenticationService.userLogin(testLoginDTO());
        assertThat(200).isEqualTo(response.getCode());

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
