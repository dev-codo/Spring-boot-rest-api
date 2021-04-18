package io.udemy.dougllas.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.udemy.dougllas.VendasApplication;
import io.udemy.dougllas.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/* Codificacao | Decodificacao do Token */

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expLong = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao_LocalDate = LocalDateTime.now().plusMinutes(expLong);
        Instant instant = dataHoraExpiracao_LocalDate.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getLogin()) // nome usuario
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    /* obter o Payload */
    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser() // decodificar o token
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataHoraExpiracao_Date = claims.getExpiration();
            LocalDateTime data = dataHoraExpiracao_Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {
            return false;
        }
    }

    /* obter o nome */
    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject(); // setSubject() -> gerarToken()
    }

    /* ----------------- Teste local ----------------- */
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

        boolean isTokenValido = jwtService.tokenValido(token);
        System.out.println("O token esta valido? " + isTokenValido);

        System.out.println(jwtService.obterLoginUsuario(token));

        System.out.println("obterClaims() | " + jwtService.obterClaims(token));
    }
}
