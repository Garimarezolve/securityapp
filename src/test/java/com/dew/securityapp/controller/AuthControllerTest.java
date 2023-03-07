package com.dew.securityapp.controller;

import com.dew.securityapp.SecurityDemoApplication;
import com.dew.securityapp.dto.LoginDTO;
import com.dew.securityapp.dto.SignUpDto;
import com.dew.securityapp.entity.User;
import com.dew.securityapp.model.ERole;
import com.dew.securityapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestConfiguration.class, SecurityDemoApplication.class})
@AutoConfigureMockMvc
 class AuthControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;

    @Test
     void should_register_new_user() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SignUpDto signUpDto= new SignUpDto();
        signUpDto.setEmail("testuser@gmail.com");
        signUpDto.setUsername("testuser@gmail.com");
        signUpDto.setPassword("test@123");
        signUpDto.setName("testUser");

        User user= new User();
        user.setId(101l);
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        user.setRole(ERole.ROLE_ADMIN);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        MvcResult result = mockMvc.perform(
                        post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(signUpDto)))
                .andExpect(status().isOk()) .andReturn();


    }
    @Test
     void should_not_register_existing_user() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SignUpDto signUpDto= new SignUpDto();
        signUpDto.setEmail("testuser@gmail.com");
        signUpDto.setUsername("testuser@gmail.com");
        signUpDto.setPassword("test@123");
        signUpDto.setName("testUser");
        User user= new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        user.setRole(ERole.ROLE_ADMIN);
        when(userRepository.existsByUsername(signUpDto.getUsername())).thenReturn(true);
        when( userRepository.existsByEmail(signUpDto.getEmail())).thenReturn(true);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        mockMvc.perform(
                post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(signUpDto)))
                .andExpect(status().isOk());
    }

    @Test
     void should_login_existing_user() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
         LoginDTO loginDto= LoginDTO.builder().password("test@123").usernameOrEmail("testuser@gmail.com").build();

        User existingUser=new User();
        existingUser.setId(1L);
        when(userRepository.findByUsername(loginDto.usernameOrEmail())).thenReturn(existingUser);
        mockMvc.perform(
                        post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());


    }
}
