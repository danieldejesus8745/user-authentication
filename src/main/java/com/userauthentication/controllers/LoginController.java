package com.userauthentication.controllers;

import com.userauthentication.services.LoginService;
import com.userauthentication.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping(path = "/{email}/{password}")
    public ResponseEntity<String> userAuthentication(@PathVariable("email") String email, @PathVariable("password") String password) {
        Object response = loginService.userAuthentication(email, password);

        if (response.equals(HttpStatus.UNAUTHORIZED)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Messages.MESSAGE_2.getDescription());
        }

        if (response.equals(HttpStatus.BAD_GATEWAY)) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Messages.MESSAGE_3.getDescription());
        }

        if (response.equals(HttpStatus.NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.MESSAGE_4.getDescription());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }

}
