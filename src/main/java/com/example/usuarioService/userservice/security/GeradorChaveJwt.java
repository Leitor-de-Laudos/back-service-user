package com.example.usuarioService.userservice.security;

import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class GeradorChaveJwt {
    public static void main(String[] args) {
        byte[] chave = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
        String chaveBase64 = Base64.getEncoder().encodeToString(chave);
        System.out.println("Chave JWT gerada: " + chaveBase64);
    }
}