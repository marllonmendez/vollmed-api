package com.marllonmendez.voll.domain.Paciente.dto;

import com.marllonmendez.voll.domain.Endereco.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {}
