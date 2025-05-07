package com.example.usuarioService.userservice.service;


import com.example.usuarioService.userservice.dto.request.UsuarioRequestDTO;
import com.example.usuarioService.userservice.dto.response.UsuarioResponseDTO;
import com.example.usuarioService.userservice.entity.Usuario;
import com.example.usuarioService.userservice.exception.UsuarioNaoEncontradoException;
import com.example.usuarioService.userservice.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setSenha(dto.getSenha()); // em app real, hashear isso!

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dto.getNome() != null) {
            usuario.setNome(dto.getNome());
        }

        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }

        if (dto.getTelefone() != null) {
            usuario.setTelefone(dto.getTelefone());
        }

        if (dto.getSenha() != null) {
            usuario.setSenha(dto.getSenha());
        }

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }


    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        return dto;
    }
}
