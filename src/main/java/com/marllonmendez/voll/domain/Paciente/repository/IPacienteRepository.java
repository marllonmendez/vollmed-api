package com.marllonmendez.voll.domain.Paciente.repository;

import com.marllonmendez.voll.domain.Paciente.entity.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByDisponivelTrue(Pageable pageable);

    @Query("select p.disponivel from Pacientes p where p.id = :idPaciente")
    Boolean findDisponivelById(Long idPaciente);
}
