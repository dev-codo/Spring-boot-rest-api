package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Usuario;
import io.udemy.dougllas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor // THIS...
public class UsuarioController {

    private final UsuarioServiceImpl userServiceImpl;
    private final PasswordEncoder pEncoder;

    /* OR, this without declaring final on the objects above */
//    public UsuarioController(UsuarioServiceImpl userServiceImpl, PasswordEncoder pEncoder) {
//        this.userServiceImpl = userServiceImpl;
//        this.pEncoder = pEncoder;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = pEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return userServiceImpl.salvar(usuario);
    }
}
