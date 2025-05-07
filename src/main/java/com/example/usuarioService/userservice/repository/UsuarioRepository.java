package com.example.usuarioService.userservice.repository;

import com.example.usuarioService.userservice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
