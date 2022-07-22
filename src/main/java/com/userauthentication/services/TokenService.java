package com.userauthentication.services;

import com.userauthentication.entities.AuthenticationToken;
import com.userauthentication.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void addToken(UUID uuid) {
        AuthenticationToken authenticationTokenFound = getToken(uuid);

        if (Objects.nonNull(authenticationTokenFound)) {
            tokenRepository.deleteById(authenticationTokenFound.getUuid());
        }

        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setOwner(uuid);
        authenticationToken.setExpiration(System.currentTimeMillis() + 120000);
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(UUID uuid) {
        AuthenticationToken authenticationToken = tokenRepository.findByOwner(uuid).orElse(null);

        if (Objects.isNull(authenticationToken)) {
            return null;
        }

        return authenticationToken;
    }

    public boolean validateToken(String token) {
        AuthenticationToken authenticationToken = getToken(UUID.fromString(token));

        if (Objects.isNull(authenticationToken)) {
            return false;
        }

        return authenticationToken.getExpiration() > System.currentTimeMillis();
    }

}
