package com.Alura.Forum.Hub;

import com.Alura.Forum.Hub.TopicoRequest;
import com.Alura.Forum.Hub.TopicoResponse;
import com.Alura.Forum.Hub.TopicoService;
import com.Alura.Forum.Hub.TopicoAtualizacaoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    // Cadastro de novo tópico
    @PostMapping
    public ResponseEntity<TopicoResponse> criar(@RequestBody @Valid TopicoRequest request) {
        try {
            TopicoResponse response = service.criar(request);
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Listagem de todos os tópicos
    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // Detalhamento de tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> buscarPorId(@PathVariable Long id) {
        try {
            TopicoResponse response = service.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TopicoAtualizacaoRequest request) {
        try {
            TopicoResponse atualizado = service.atualizar(id, request);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = service.excluir(id);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
