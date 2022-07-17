package com.userauthentication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(nullable = false, length = 40)
    private UUID owner;

    @Column(nullable = false)
    private long expiration;

}
