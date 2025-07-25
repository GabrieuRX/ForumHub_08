package com.Alura.Forum.Hub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaGenerator {
    public static void main(String[] args) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode("123456");
        System.out.println(senhaCriptografada);
    }
}