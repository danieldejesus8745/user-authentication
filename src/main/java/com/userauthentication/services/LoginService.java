package com.userauthentication.services;

import com.userauthentication.dto.UserDTO;
import com.userauthentication.repositories.rest.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenService tokenService;
    private final UserRegistrationRepository userRegistrationRepository;

    public Object userAuthentication(String email, String password) {
        ResponseEntity<UserDTO> response = getUser(email, password);

        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            return HttpStatus.UNAUTHORIZED;
        }

        if (response.getStatusCode().equals(HttpStatus.BAD_GATEWAY)) {
            return HttpStatus.BAD_GATEWAY;
        }

        if (Objects.isNull(response.getBody())) {
            return HttpStatus.NOT_FOUND;
        }

        tokenService.addToken(response.getBody().getUuid());

        return response.getBody().getUuid().toString();
    }

    private ResponseEntity<UserDTO> getUser(String email, String password) {
        return userRegistrationRepository.getUser(email, password);
    }

}
