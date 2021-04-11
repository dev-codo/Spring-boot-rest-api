package io.udemy.dougllas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
//    @Getter(onMethod = @__({@JsonIgnore})) // nao mostrar senha (no corpo da resposta)
//    @Setter(onMethod = @__({@JsonProperty})) // setar senha
    private String senha;

    private boolean admin;

    /* admin: Getter | Setter --> for ref. */
//    public boolean isAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(boolean admin) {
//        this.admin = admin;
//    }
}
