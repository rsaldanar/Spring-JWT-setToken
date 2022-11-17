package com.example.springokta.repository;

import com.example.springokta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findOndByEmail(String email);


}
