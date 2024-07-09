package com.marllonmendez.voll.domain.Usuario.repository;

import com.marllonmendez.voll.domain.Usuario.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String loign);
}
