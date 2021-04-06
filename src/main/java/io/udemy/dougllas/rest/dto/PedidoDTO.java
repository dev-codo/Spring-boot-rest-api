package io.udemy.dougllas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO { // Data Transfer Object
    private Integer cliente; // nao pode ter nome diferente (ex: idCliente), senao da erro com  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;

    /* replaced with LOMBOK annotations above */
//    public Integer getIdCliente() {
//        return idCliente;
//    }
//
//    public void setIdCliente(Integer idCliente) {
//        this.idCliente = idCliente;
//    }
//
//    public BigDecimal getTotal() {
//        return total;
//    }
//
//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }
//
//    public List<ItemPedidoDTO> getItens() {
//        return itens;
//    }
//
//    public void setItens(List<ItemPedidoDTO> itens) {
//        this.itens = itens;
//    }
}
