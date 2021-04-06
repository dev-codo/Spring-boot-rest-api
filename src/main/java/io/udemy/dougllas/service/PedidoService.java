package io.udemy.dougllas.service;

import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
