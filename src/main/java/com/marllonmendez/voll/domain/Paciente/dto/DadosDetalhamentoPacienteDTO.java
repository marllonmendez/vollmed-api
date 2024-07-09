package com.marllonmendez.voll.domain.Paciente.dto;

import com.marllonmendez.voll.domain.Endereco.entity.Endereco;
import com.marllonmendez.voll.domain.Paciente.entity.Paciente;

public record DadosDetalhamentoPacienteDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }

}
