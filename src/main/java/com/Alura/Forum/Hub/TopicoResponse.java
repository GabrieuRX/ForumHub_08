package com.Alura.Forum.Hub;

import java.time.LocalDateTime;

public record TopicoResponse<ativo>(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso,
        LocalDateTime dataCriacao,
        Boolean ativo
) {}
