package com.Alura.Forum.Hub.controller;

import com.Alura.Forum.Hub.domain.usuario.DadosAutenticacao;
import com.Alura.Forum.Hub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // autentica o usuário
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        Authentication authentication = manager.authenticate(authenticationToken);

        // gera o token com base no usuário autenticado
        String token = tokenService.gerarToken((String) authentication.getPrincipal());

        return token;
    }
}
