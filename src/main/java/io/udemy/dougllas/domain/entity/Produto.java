package io.udemy.dougllas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo descricao obrigatorio")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "Campo preco eh obrigatorio")
    private BigDecimal preco;


/*NAO EXISTE na entidade Produto:

@OneToMany(mappedBy = "produto")
private List<ItemPedido> items;

Produto nao precisa mapear uma colecao para ItemPedido, pq um produto (ex: Caneta) nao precisa apresentar todos os itensPedidos.

O Produto so serao referenciados por ItemPedidos qdo existir uma venda (Pedido). O ItemPedido so existe qdo existe um Pedido.
*/
//    ----------------------------------------------------------
    /* replaced with LOMBOK */
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//
//    public BigDecimal getPreco() {
//        return preco;
//    }
//
//    public void setPreco(BigDecimal preco) {
//        this.preco = preco;
//    }
}
