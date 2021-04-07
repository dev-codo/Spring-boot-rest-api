package io.udemy.dougllas.rest.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *  DTO para visualizacao do client-side
 *  {
 *     "codigo": 9,
 *     "cpf": "12345",
 *     "nomeCliente": "TomTom",
 *     "total": 25.00,
 *     "dataPedido": "06/04/2021",
 *     "itens": [
 *          {
 *             "descricaoProduto": "Mouse",
 *             "precoUnitario": 25.00,
 *             "quantidade": 1
 *          }
 *      ]
 *  }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoItemPedidoDTO {
    private String descricaoProduto; // pega atraves do Produto
    private BigDecimal precoUnitario; // pega atraves do Produto
    private Integer quantidade;
}
