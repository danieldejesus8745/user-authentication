package com.userauthentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.userauthentication.dto.UserDTO;
import com.userauthentication.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenService tokenService;

    public static final String userRegistrationGetUserEndpoint =
            "https://my-user-registration.herokuapp.com/api/v1/users/";

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
