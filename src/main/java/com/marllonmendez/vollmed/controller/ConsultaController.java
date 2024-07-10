package com.marllonmendez.vollmed.controller;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.dto.DetalhadosConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.repository.IConsultaRepository;
import com.marllonmendez.vollmed.domain.Colsulta.service.AgendaDeConsulta;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consultas")
public class ConsultaController {

    @Autowired
    private IConsultaRepository iConsultaRepository;

    @Autowired
    private AgendaDeConsulta agendaDeConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid AgendamentoConsultaDTO dto) {
        var dadosConsulta = agendaDeConsulta.agendar(dto);
        return ResponseEntity.ok(dadosConsulta);
    }

    @GetMapping
    public ResponseEntity<Page<DetalhadosConsultaDTO>> listarConsultas(@PageableDefault(size = 3, sort = {"data"}) Pageable pageable) {
        var lista = agendaDeConsulta.listarConsultas(pageable);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity canselarConsulta(@PathVariable Long id) {
        var consulta = iConsultaRepository.getReferenceById(id);
        consulta.remover();
        return ResponseEntity.noContent().build();
    }

}

