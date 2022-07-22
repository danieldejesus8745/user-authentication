package com.userauthentication.services;

import com.userauthentication.dto.UserDTO;
import com.userauthentication.repositories.rest.UserRegistrationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private TokenService tokenService;
    @Mock
    private UserRegistrationRepository userRegistrationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustAuthenticateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(UUID.randomUUID());
        ResponseEntity<UserDTO> responseUserDTO = ResponseEntity.status(HttpStatus.OK).body(userDTO);
        when(userRegistrationRepository.getUser(any(), any())).thenReturn(responseUserDTO);
        Object testResult = loginService.userAuthentication("email@email.com", "secret");
        Assertions.assertNotNull(testResult);
    }

}
