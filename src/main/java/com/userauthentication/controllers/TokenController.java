package com.userauthentication.controllers;

import com.userauthentication.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/tokens")
public class TokenController {

    private final TokenService tokenService;

    @GetMapping(path = "/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String token) {
        boolean response = tokenService.validateToken(token);

        if (!response) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Boolean.FALSE);
        }

        return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
    }

}
