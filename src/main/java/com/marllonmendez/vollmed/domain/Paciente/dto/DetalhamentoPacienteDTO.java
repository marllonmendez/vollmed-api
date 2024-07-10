package com.marllonmendez.vollmed.domain.Paciente.dto;

import com.marllonmendez.vollmed.domain.Endereco.entity.Endereco;
import com.marllonmendez.vollmed.domain.Paciente.entity.Paciente;

public record DetalhamentoPacienteDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }

}
