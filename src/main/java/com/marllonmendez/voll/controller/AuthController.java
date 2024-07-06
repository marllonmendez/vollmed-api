package com.marllonmendez.voll.controller;

import com.marllonmendez.voll.dto.DadosAuthDTO;

import com.marllonmendez.voll.modal.Usuario;
import com.marllonmendez.voll.service.Token;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Token tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuthDTO dadosAuthDTO) {
        var token = new UsernamePasswordAuthenticationToken(dadosAuthDTO.login(), dadosAuthDTO.senha());
        var auth = authenticationManager.authenticate(token);
        return ResponseEntity.ok(tokenService.gerarToken((Usuario) auth.getPrincipal()));
    }

}
