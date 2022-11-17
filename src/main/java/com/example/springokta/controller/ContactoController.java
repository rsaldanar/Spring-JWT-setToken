package com.example.springokta.controller;

import com.example.springokta.entity.Contacto;
import com.example.springokta.repository.ContactoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@RestController
@RequestMapping("/contactos")
@AllArgsConstructor
public class ContactoController {

    private final ContactoRepository contactoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contacto> getAllContactos() {
        return contactoRepository.findAll();
    }


}
