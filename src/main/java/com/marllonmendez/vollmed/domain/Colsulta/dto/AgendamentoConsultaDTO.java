package com.marllonmendez.vollmed.domain.Colsulta.dto;

import com.marllonmendez.vollmed.enums.Ramo;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Ramo ramo) {
}
