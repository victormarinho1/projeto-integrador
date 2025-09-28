package com.senac.demo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("O e-mail já está cadastrado.");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com e-mail: " + email));
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }


    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setSobrenome(usuarioAtualizado.getSobrenome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setFuncao(usuarioAtualizado.getFuncao());
        usuarioExistente.setAtivo(usuarioAtualizado.getAtivo());
        usuarioExistente.setData_atualizacao(LocalDateTime.now());

        return usuarioRepository.save(usuarioExistente);
    }

    public void trocar_status(Long id) {
        Usuario usuarioExistente = buscarPorId(id);
        usuarioExistente.setAtivo(Boolean.valueOf(!usuarioExistente.getAtivo()));
        usuarioRepository.save(usuarioExistente);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
