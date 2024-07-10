package com.marllonmendez.vollmed.domain.Usuario.repository;

import com.marllonmendez.vollmed.domain.Usuario.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String loign);
}
