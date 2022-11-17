package com.example.springokta.security;

import com.example.springokta.entity.Usuario;//
import com.example.springokta.repository.UsuarioRepository;//
import org.springframework.beans.factory.annotation.Autowired;//
import org.springframework.security.core.userdetails.UserDetails;//
import org.springframework.security.core.userdetails.UserDetailsService;//
import org.springframework.security.core.userdetails.UsernameNotFoundException;//
import org.springframework.stereotype.Service;//

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findOndByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El Usuario con el email: " + email + " no existe."));
        return new UserDetailImpl(usuario);
    }

}
