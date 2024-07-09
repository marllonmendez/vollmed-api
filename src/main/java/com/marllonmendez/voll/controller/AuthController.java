package com.marllonmendez.voll.controller;

import com.marllonmendez.voll.domain.Usuario.dto.DadosAuthDTO;

import com.marllonmendez.voll.domain.Usuario.entity.Usuario;
import com.marllonmendez.voll.domain.Usuario.service.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Token tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuthDTO authDTO) {
        var token = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.senha());
        var auth = authenticationManager.authenticate(token);
        return ResponseEntity.ok(tokenService.gerarToken((Usuario) auth.getPrincipal()));
    }

}
