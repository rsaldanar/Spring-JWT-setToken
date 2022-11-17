package com.example.springokta.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@Entity
@Table(name = "usuario")
@Data
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


}
