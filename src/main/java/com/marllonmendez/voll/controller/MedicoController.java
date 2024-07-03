package com.marllonmendez.voll.controller;

import com.marllonmendez.voll.dto.DadosMedicoDTO;
import com.marllonmendez.voll.dto.AtualizacaoDadosMedicoDTO;
import com.marllonmendez.voll.dto.MedicoDTO;
import com.marllonmendez.voll.modal.Medico;
import com.marllonmendez.voll.repository.IMedicoRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private IMedicoRepository imedicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medicoDTO) {
        imedicoRepository.save(new Medico(medicoDTO));
    }

    //    http://localhost:8080/medicos?sort=cmr,desc&size=2&page=1
    @GetMapping
    public Page<DadosMedicoDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return imedicoRepository.findAllByDisponivelTrue(pageable).map(DadosMedicoDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoDadosMedicoDTO atualizacaoDadosMedicoDTO) {
        var medico = imedicoRepository.getReferenceById(atualizacaoDadosMedicoDTO.Id());
        medico.atualizarMedico(atualizacaoDadosMedicoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var medico = imedicoRepository.getReferenceById(id);
        medico.remover();
    }

}
