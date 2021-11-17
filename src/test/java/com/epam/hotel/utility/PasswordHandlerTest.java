package com.epam.hotel.utility;


import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;
import org.junit.jupiter.api.Test;

class PasswordHandlerTest {

    @Test
    void encryptPassword() {
        User user = new User(0, "Igor", "Taren", UserType.ADMIN, "user@tut.by", "12345678");
        System.out.println("hash->" + user.hashCode());

        String userHash = String.valueOf(user.hashCode());
        StringBuffer key = new StringBuffer();
        key.append(userHash).append(userHash).append(userHash);

        System.out.println("key->" + key.toString());

        PasswordHandler passwordHandler = new PasswordHandler().setEncryptionKey(1111111111);
        String encrPass = passwordHandler.encryptPassword("478374Taren");
        System.out.println("encrPass->" + encrPass);
    }

    @Test
    void decryptPassword() {
        User user = new User(0, "Igor", "Taren", UserType.ADMIN, "user@tut.by", "12345678");
        System.out.println("hash->" + user.hashCode());

        PasswordHandler passwordHandler = new PasswordHandler().setEncryptionKey(1111111111);
        String decrPass = passwordHandler.decryptPassword("CBgMQFUBWGPEK+ukuE8OHQ==");
        System.out.println("descrPass->" + decrPass);

    }
}