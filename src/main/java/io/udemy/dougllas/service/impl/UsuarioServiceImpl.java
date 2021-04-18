package io.udemy.dougllas.service.impl;

import io.udemy.dougllas.domain.entity.Usuario;
import io.udemy.dougllas.domain.repository.UsuarioRepository;
import io.udemy.dougllas.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder pEncoder;

    @Autowired
    private UsuarioRepository userRepo;

    /* Verificar senha digitada com a senha criptografada em DB - ${security.jwt.chave-assinatura} */
    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = pEncoder.matches(usuario.getSenha(), user.getPassword());

        if (senhasBatem) {
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /* Mock Db */
//        if(!username.equals("tomtom")) {
//            throw new UsernameNotFoundException("Usuario nao encontrado na base.");
//        }
//
//        return User
//                .builder()
//                .username("tomtom")
//                .password(pEncoder.encode("111"))
//                .roles("USER", "ADMIN")
//                .build();

        Usuario usuario = userRepo.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado na DB."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User  // import org.springframework.security.core.userdetails.User;
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public Usuario salvar(Usuario user) {
        return userRepo.save(user);
    }
}
