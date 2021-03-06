package com.userauthentication.repositories;

import com.userauthentication.entities.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, UUID> {

    Optional<AuthenticationToken> findByOwner(UUID uuid);

}
