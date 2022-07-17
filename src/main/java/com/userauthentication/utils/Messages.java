package com.userauthentication.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {

    MESSAGE_1("Erro ao chamar módulo USER REGISTRATION. "),
    MESSAGE_2("Usuário ou senha inválida"),
    MESSAGE_3("Serviço de login indisponível"),
    MESSAGE_4("Usuário não encontrado");

    private final String description;
}
