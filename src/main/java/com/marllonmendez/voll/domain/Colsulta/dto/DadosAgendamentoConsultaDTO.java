package com.marllonmendez.voll.domain.Colsulta.dto;

import com.marllonmendez.voll.enums.Ramo;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDTO(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Ramo ramo) {
}
