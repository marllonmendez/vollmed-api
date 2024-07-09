package com.marllonmendez.voll.domain.Medico.dto;

import com.marllonmendez.voll.domain.Endereco.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoDadosMedicoDTO(
        @NotNull
        Long Id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {}
