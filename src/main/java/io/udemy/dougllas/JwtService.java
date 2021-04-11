package io.udemy.dougllas;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.udemy.dougllas.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expLong = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expLong);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getLogin()) // nome usuario
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    /* Teste local */
    public static void main(String[] args) {
        /*
        * JwtService jwtService = new JwtService() -> null
        *
        * expiracao e chaveAssinatura pertencem ao contexto de injecao do Spring Boot.
        * eh necessário iniciar o App antes:
        * SpringApplication.run(VendasApplication.class) -> objeto que eh o contexto da aplicação.
        * o método run() retorna o contexto, e do contexto consegue-se obter qualquer Bean (objeto JwtService)
        */

        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService jwtService = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("mingau").build(); // builder() do Lombok p/criacao d'um objeto Usuario
        String token = jwtService.gerarToken(usuario);
        System.out.println(token);
    }
}
