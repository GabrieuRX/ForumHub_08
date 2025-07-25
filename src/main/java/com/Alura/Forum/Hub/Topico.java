package com.Alura.Forum.Hub;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;
    private LocalDateTime dataCriacao;
    private Boolean ativo;

    public Topico() {}

    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
