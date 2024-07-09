package com.marllonmendez.voll.domain.Medico.repository;

import com.marllonmendez.voll.domain.Medico.entity.Medico;

import com.marllonmendez.voll.enums.Ramo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface IMedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByDisponivelTrue(Pageable pageable);

    @Query("select m from Medicos m where m.disponivel = true and m.ramo = :ramo and m.id not in(select c.medico.id from Consultas c where c.data = :data) order by rand() limit 1")
    Medico medicoLivreAleatorio(Ramo ramo, LocalDateTime data);

    @Query("select m.disponivel from Medicos m where m.id = :idMedico")
    Boolean findDisponivelById(Long idMedico);
}
