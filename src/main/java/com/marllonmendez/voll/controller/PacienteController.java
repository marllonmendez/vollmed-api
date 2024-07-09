package com.marllonmendez.voll.controller;

import com.marllonmendez.voll.domain.Paciente.dto.DadosAtualizacaoPacienteDTO;
import com.marllonmendez.voll.domain.Paciente.dto.DadosCadastroPacienteDTO;
import com.marllonmendez.voll.domain.Paciente.dto.DadosDetalhamentoPacienteDTO;
import com.marllonmendez.voll.domain.Paciente.dto.DadosListagemPacienteDTO;
import com.marllonmendez.voll.domain.Paciente.entity.Paciente;
import com.marllonmendez.voll.domain.Paciente.repository.IPacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dto, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dto);
        iPacienteRepository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = iPacienteRepository.findAllByDisponivelTrue(pageable).map(DadosListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPacienteDetalhado(@PathVariable Long id) {
        var paciente = iPacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPacienteDTO dto) {
        var paciente = iPacienteRepository.getReferenceById(dto.id());
        paciente.atualizarPaciente(dto);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerPaciente(@PathVariable Long id) {
        var paciente = iPacienteRepository.getReferenceById(id);
        paciente.remover();
        return ResponseEntity.noContent().build();
    }



}
