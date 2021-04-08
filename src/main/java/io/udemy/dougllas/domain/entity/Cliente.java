package io.udemy.dougllas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Nome eh obrigatorio!") // Bean validation
    private String nome;

//    @CPF(message = "Informe um CPF valido")
    @NotEmpty(message = "Cpf eh obrigatorio!")
    @Column(length = 5)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /* replaced with LOMBOK */
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public Set<Pedido> getPedidos() {
//        return pedidos;
//    }
//
//    public void setPedidos(Set<Pedido> pedidos) {
//        this.pedidos = pedidos;
//    }
//
//    @Override
//    public String toString() {
//        return "Cliente{" +
//                "id=" + id +
//                ", nome='" + nome + '\'' +
//                ", cpf='" + cpf + '\'' +
//                '}';
//    }
}
