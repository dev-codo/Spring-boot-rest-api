package io.udemy.dougllas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * POST criado no Postman para visualização da DTO
 * {
 *     "cliente" : 1,
 *     "total" : 99,
 *     "itens" : [
 *         {
 *             "produto" : 1,
 *             "quantidade" : 10
 *         }
 *     ]
 * }
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO { // Data Transfer Object
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
