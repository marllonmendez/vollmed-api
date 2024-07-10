package com.marllonmendez.vollmed.controller;

import com.marllonmendez.vollmed.domain.Paciente.dto.AtualizacaoPacienteDTO;
import com.marllonmendez.vollmed.domain.Paciente.dto.CadastroPacienteDTO;
import com.marllonmendez.vollmed.domain.Paciente.dto.DetalhamentoPacienteDTO;
import com.marllonmendez.vollmed.domain.Paciente.dto.ListagemPacienteDTO;
import com.marllonmendez.vollmed.domain.Paciente.entity.Paciente;
import com.marllonmendez.vollmed.domain.Paciente.repository.IPacienteRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Pacientes")
public class PacienteController {

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid CadastroPacienteDTO dto, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dto);
        iPacienteRepository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDTO>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = iPacienteRepository.findAllByDisponivelTrue(pageable).map(ListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPacienteDetalhado(@PathVariable Long id) {
        var paciente = iPacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid AtualizacaoPacienteDTO dto) {
        var paciente = iPacienteRepository.getReferenceById(dto.id());
        paciente.atualizarPaciente(dto);
        return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerPaciente(@PathVariable Long id) {
        var paciente = iPacienteRepository.getReferenceById(id);
        paciente.remover();
        return ResponseEntity.noContent().build();
    }



}
