package com.Alura.Forum.Hub;

import com.Alura.Forum.Hub.TopicoRequest;
import com.Alura.Forum.Hub.TopicoResponse;
import com.Alura.Forum.Hub.Topico;
import com.Alura.Forum.Hub.TopicoAtualizacaoRequest;
import com.Alura.Forum.Hub.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public TopicoResponse criar(TopicoRequest dados) {
        boolean existeDuplicado = repository.existsByTituloAndMensagem(
                dados.titulo(), dados.mensagem()
        );

        if (existeDuplicado) {
            throw new IllegalArgumentException("Tópico duplicado.");
        }

        Topico topico = new Topico(
                dados.titulo(),
                dados.mensagem(),
                dados.autor(),
                dados.curso()
        );

        repository.save(topico);

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getAtivo()
        );
    }

    public List<TopicoResponse> listar() {
        return repository.findAll().stream().map(topico ->
                new TopicoResponse(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getAutor(),
                        topico.getCurso(),
                        topico.getDataCriacao(),
                        topico.getAtivo()
                )
        ).collect(Collectors.toList());
    }

    public TopicoResponse buscarPorId(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado"));

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getAtivo()
        );
    }

    public TopicoResponse atualizar(Long id, TopicoAtualizacaoRequest dados) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado"));

        boolean duplicado = repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if (duplicado && (!topico.getTitulo().equals(dados.titulo()) || !topico.getMensagem().equals(dados.mensagem()))) {
            throw new IllegalArgumentException("Já existe um tópico com o mesmo título e mensagem.");
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(dados.autor());
        topico.setCurso(dados.curso());

        repository.save(topico);

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getAtivo()
        );
    }
    public boolean excluir(Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
