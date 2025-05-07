package com.example.usuarioService.userservice.controller;

import com.example.usuarioService.userservice.security.JwtUtil;
import com.example.usuarioService.userservice.dto.request.AuthRequestUser;
import com.example.usuarioService.userservice.entity.Usuario;
import com.example.usuarioService.userservice.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthControllerUser {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthControllerUser(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestUser auth) {
        Optional<Usuario> optionalUser = usuarioRepository.findByEmail(auth.getEmail());

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            if (user.getSenha().equals(auth.getSenha())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok().body(token);
            }
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}