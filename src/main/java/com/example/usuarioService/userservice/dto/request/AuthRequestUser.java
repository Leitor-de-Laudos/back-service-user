package com.example.usuarioService.userservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestUser {
    private String email;
    private String senha;
}