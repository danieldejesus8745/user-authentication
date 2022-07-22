package com.userauthentication.repositories.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.userauthentication.dto.UserDTO;
import com.userauthentication.repositories.rest.UserRegistrationRepository;
import com.userauthentication.utils.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class UserRepositoryImpl implements UserRegistrationRepository {

    public static final String userRegistrationGetUserEndpoint =
            "https://my-user-registration.herokuapp.com/api/v1/users/";

    @Override
    public ResponseEntity<UserDTO> getUser(String email, String password) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userRegistrationGetUserEndpoint + email + "/" + password))
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpStatus.UNAUTHORIZED.value()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            UserDTO userDTO = objectMapper.readValue(response.body(), UserDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (IOException | InterruptedException exception) {
            log.error(Messages.MESSAGE_1.getDescription(), exception);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }
    }

}
