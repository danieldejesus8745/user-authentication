package com.userauthentication.controllers;

import com.userauthentication.services.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TokenControllerTest {

    @InjectMocks
    private TokenController tokenController;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldValidateToken() {
        when(tokenService.validateToken(any())).thenReturn(true);
        ResponseEntity<Boolean> testResult = tokenController.validateToken("");
        Assertions.assertEquals(HttpStatus.OK.value(), testResult.getStatusCodeValue());
    }

    @Test
    void shouldFailWhenValidtingToken() {
        ResponseEntity<Boolean> testResult = tokenController.validateToken("");
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), testResult.getStatusCodeValue());
    }

}
