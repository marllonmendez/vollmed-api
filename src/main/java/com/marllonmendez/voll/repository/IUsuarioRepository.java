package com.marllonmendez.voll.repository;

import com.marllonmendez.voll.modal.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String loign);

}
