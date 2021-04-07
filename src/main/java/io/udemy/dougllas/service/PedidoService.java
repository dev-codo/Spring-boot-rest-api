package io.udemy.dougllas.service;

import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    /* Optional: pq pode nao existir esse id */
    Optional<Pedido> obterPedidoCompleto(Integer id);
}
