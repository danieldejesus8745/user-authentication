package com.userauthentication.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessagesTest {

    @Test
    void shouldReturnAMessage() {
        String resultTest = Messages.MESSAGE_1.getDescription();
        Assertions.assertEquals(Messages.MESSAGE_1.getDescription(), resultTest);
    }

}
