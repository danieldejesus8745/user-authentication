package com.userauthentication.controllers;

import com.userauthentication.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPerformAnUserAuthentication() {
        when(loginService.userAuthentication(any(), any())).thenReturn("dummy-owner-token");
        loginController.userAuthentication("", "");
        verify(loginService, Mockito.times(1)).userAuthentication(any(), any());
    }

    @Test
    void shouldReturnHttpStatusUnauthorized() {
        when(loginService.userAuthentication(any(), any())).thenReturn(HttpStatus.UNAUTHORIZED);
        loginController.userAuthentication("", "");
        verify(loginService, Mockito.times(1)).userAuthentication(any(), any());
    }

    @Test
    void shouldReturnHttpStatusBadRequest() {
        when(loginService.userAuthentication(any(), any())).thenReturn(HttpStatus.BAD_GATEWAY);
        loginController.userAuthentication("", "");
        verify(loginService, Mockito.times(1)).userAuthentication(any(), any());
    }

    @Test
    void shouldReturnHttpStatusNotFound() {
        when(loginService.userAuthentication(any(), any())).thenReturn(HttpStatus.NOT_FOUND);
        loginController.userAuthentication("", "");
        verify(loginService, Mockito.times(1)).userAuthentication(any(), any());
    }

}
