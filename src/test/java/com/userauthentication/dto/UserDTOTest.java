package com.userauthentication.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void shouldGetUuid() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(UUID.randomUUID());
        Assertions.assertNotNull(userDTO.getUuid());
    }

    @Test
    void shouldGetName() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("User Name");
        Assertions.assertNotNull(userDTO.getName());
    }

    @Test
    void shouldGetEmail() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        Assertions.assertNotNull(userDTO.getEmail());
    }

    @Test
    void shouldGetPassword() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("123456");
        Assertions.assertNotNull(userDTO.getPassword());
    }

    @Test
    void shouldGetCreatedAt() {
        UserDTO userDTO = new UserDTO();
        userDTO.setCreatedAt(LocalDate.now());
        Assertions.assertNotNull(userDTO.getCreatedAt());
    }

    @Test
    void shouldInstantiateAnUserDTOWithAllArguments() {
        UserDTO userDTO = new UserDTO(null, "", "", "", null);
        Assertions.assertNotNull(userDTO);
    }

}
