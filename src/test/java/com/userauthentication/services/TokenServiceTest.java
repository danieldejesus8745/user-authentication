package com.userauthentication.services;

import com.userauthentication.entities.AuthenticationToken;
import com.userauthentication.repositories.TokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private TokenRepository tokenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddToken() {
        tokenService.addToken(UUID.randomUUID());
        verify(tokenRepository, times(1)).save(any());
    }

    @Test
    void shouldGetToken() {
        UUID uuid = UUID.randomUUID();
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setUuid(uuid);
        when(tokenRepository.findByOwner(any())).thenReturn(Optional.of(authenticationToken));
        AuthenticationToken testResult = tokenService.getToken(uuid);
        Assertions.assertEquals(uuid, testResult.getUuid());
    }

    @Test
    void shouldFailWhenValidatingToken() {
        boolean testResult = tokenService.validateToken("8897884f-e178-4f65-83c3-8182d99b72ae");
        assertFalse(testResult);
    }

}
