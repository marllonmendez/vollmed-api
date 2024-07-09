package com.marllonmendez.voll.controller;

import com.marllonmendez.voll.domain.Colsulta.dto.DadosAgendamentoConsultaDTO;
import com.marllonmendez.voll.domain.Colsulta.dto.DadosDetalhadosConsultaDTO;
import com.marllonmendez.voll.domain.Colsulta.repository.IConsultaRepository;
import com.marllonmendez.voll.domain.Colsulta.service.AgendaDeConsulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaRepository iConsultaRepository;

    @Autowired
    private AgendaDeConsulta agendaDeConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsultaDTO dto) {
        var dadosConsulta = agendaDeConsulta.agendar(dto);
        return ResponseEntity.ok(dadosConsulta);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhadosConsultaDTO>> listarConsultas(@PageableDefault(size = 3, sort = {"data"}) Pageable pageable) {
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

