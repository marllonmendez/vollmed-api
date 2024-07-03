package com.marllonmendez.voll.repository;

import com.marllonmendez.voll.modal.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByDisponivelTrue(Pageable pageable);
}
