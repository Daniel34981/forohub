package com.daniel.forohub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorDeHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123456");
        System.out.println("Hash BCrypt: " + hash);
    }
}