package com.marllonmendez.voll.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoDadosMedicoDTO(

        @NotNull
        Long Id,
        String nome,
        String telefone,
        EnderecoDTO endereco

) {}
