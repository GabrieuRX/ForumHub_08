package com.Alura.Forum.Hub;
import jakarta.validation.constraints.NotBlank;


public record TopicoAtualizacaoRequest(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String autor,
        @NotBlank String curso
) {}
