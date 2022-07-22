package com.userauthentication.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AuthenticationTokenTest {

    @Test
    void shouldGetUuid() {
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setUuid(UUID.randomUUID());
        Assertions.assertNotNull(authenticationToken.getUuid());
    }

    @Test
    void shouldGetOwner() {
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setOwner(UUID.randomUUID());
        Assertions.assertNotNull(authenticationToken.getOwner());
    }

    @Test
    void shouldGetExpiration() {
        long expiration = System.currentTimeMillis();
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setExpiration(expiration);
        Assertions.assertEquals(expiration, authenticationToken.getExpiration());
    }

    @Test
    void shouldInstantiateAnAuthenticationTokenWithAllArguments() {
        AuthenticationToken authenticationToken =
                new AuthenticationToken(null, null, System.currentTimeMillis());
        Assertions.assertNotNull(authenticationToken);
    }

}
