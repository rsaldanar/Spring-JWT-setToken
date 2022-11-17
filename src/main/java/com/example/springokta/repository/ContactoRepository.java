package com.example.springokta.repository;

import com.example.springokta.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
