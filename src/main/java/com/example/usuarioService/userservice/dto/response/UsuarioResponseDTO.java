package com.example.usuarioService.userservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UsuarioResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;

}

