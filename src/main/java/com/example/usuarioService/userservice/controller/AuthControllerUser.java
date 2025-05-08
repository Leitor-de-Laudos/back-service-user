package com.example.usuarioService.userservice.controller;

import com.example.usuarioService.userservice.dto.response.AuthRespDTO;
import com.example.usuarioService.userservice.security.JwtUtil;
import com.example.usuarioService.userservice.dto.request.AuthRequestUser;
import com.example.usuarioService.userservice.entity.Usuario;
import com.example.usuarioService.userservice.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthControllerUser {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthControllerUser(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @CrossOrigin(origins = "http://localhost:5173/auth/signin")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestUser auth) {
        Optional<Usuario> optionalUser = usuarioRepository.findByEmail(auth.getEmail());

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();

            if (passwordEncoder.matches(auth.getSenha(), user.getSenha())) {
                String token = jwtUtil.generateToken(user.getEmail());
                var responseDTO = new AuthRespDTO(token);

                return ResponseEntity.ok().body(responseDTO);
            }
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}