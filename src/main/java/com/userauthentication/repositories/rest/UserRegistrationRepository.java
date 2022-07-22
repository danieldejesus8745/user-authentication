package com.userauthentication.repositories.rest;

import com.userauthentication.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserRegistrationRepository {

    ResponseEntity<UserDTO> getUser(String email, String password);

}
