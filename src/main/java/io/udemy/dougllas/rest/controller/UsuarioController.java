package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Usuario;
import io.udemy.dougllas.exception.SenhaInvalidaException;
import io.udemy.dougllas.rest.dto.CredenciaisDTO;
import io.udemy.dougllas.rest.dto.TokenDTO;
import io.udemy.dougllas.security.jwt.JwtService;
import io.udemy.dougllas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor // THIS...
public class UsuarioController {

    private final UsuarioServiceImpl userServiceImpl;
    private final PasswordEncoder pEncoder;
    private final JwtService jwtService;

    /* OR, this without declaring final (because Lombok) on the objects above */
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

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            /* obter Request body: nome e senha - Usuario */
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();

//            UserDetails usuarioAutenticado = userServiceImpl.autenticar(usuario);
            userServiceImpl.autenticar(usuario); // usuario autenticado

            /* Uma vez o usuario verificado, gerar token */
            String token = jwtService.gerarToken(usuario);

            return new TokenDTO(usuario.getLogin(), token); // retorno da POST Response body
            /* Apos, copiar token e colar na Header das Url's na Authentication > Bearer */

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
