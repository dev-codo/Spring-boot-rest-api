package io.udemy.dougllas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POST criado no Postman para visualização da DTO
 * { --> (Pedido)
 *     "cliente" : 1,
 *     "total" : 99,
 *     "itens" : [ --> (ItemPedido)
 *         {
 *             "produto" : 1,
 *             "quantidade" : 10
 *         }
 *     ]
 * }
 * em (Pedido): dataPedido sera passado no PedidoServiceImpl
 * em (ItemPedido): pedido(id) sera passado PedidoServiceImpl
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    private Integer produto; // nao pode ter nome diferente (ex: idProduto), senao da erro com  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quantidade;

    /* replaced with LOMBOK annotations above */
//    public Integer getIdProduto() {
//        return idProduto;
//    }
//
//    public void setIdProduto(Integer idProduto) {
//        this.idProduto = idProduto;
//    }
//
//    public Integer getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(Integer quantidade) {
//        this.quantidade = quantidade;
//    }
}
