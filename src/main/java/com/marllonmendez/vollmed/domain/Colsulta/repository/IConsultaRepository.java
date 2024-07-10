package com.marllonmendez.vollmed.domain.Colsulta.repository;

import com.marllonmendez.vollmed.domain.Colsulta.entity.Consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findAllByDisponivelTrue(Pageable pageable);

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
}
