package com.marllonmendez.vollmed.controller;

import com.marllonmendez.vollmed.domain.Medico.entity.Medico;
import com.marllonmendez.vollmed.domain.Medico.dto.MedicoDTO;
import com.marllonmendez.vollmed.domain.Medico.dto.DadosMedicoDTO;
import com.marllonmendez.vollmed.domain.Medico.dto.DetalhadosMedicoDTO;
import com.marllonmendez.vollmed.domain.Medico.dto.AtualizacaoDadosMedicoDTO;
import com.marllonmendez.vollmed.domain.Medico.repository.IMedicoRepository;

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
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Médicos")
public class MedicoController {

    @Autowired
    private IMedicoRepository imedicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriBuilder) {
        var medico =  new Medico(medicoDTO);
        imedicoRepository.save(medico);
        var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhadosMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosMedicoDTO>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var lista = imedicoRepository.findAllByDisponivelTrue(pageable).map(DadosMedicoDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarMedicoDetalhado(@PathVariable Long id) {
        var medico = imedicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhadosMedicoDTO(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizacaoDadosMedicoDTO atualizacaoDadosMedicoDTO) {
        var medico = imedicoRepository.getReferenceById(atualizacaoDadosMedicoDTO.Id());
        medico.atualizarMedico(atualizacaoDadosMedicoDTO);
        return ResponseEntity.ok(new DetalhadosMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerMedico(@PathVariable Long id) {
        var medico = imedicoRepository.getReferenceById(id);
        medico.remover();
        return ResponseEntity.noContent().build();
    }

}
