package com.senac.demo.usuario;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false, length = 50)
    private UsuarioFuncao funcao = UsuarioFuncao.DENUNCIANTE;

    @Column(nullable = false)
    private Boolean ativo = (Boolean) true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime data_criacao = LocalDateTime.now();

    @Column
    private LocalDateTime data_atualizacao;
}