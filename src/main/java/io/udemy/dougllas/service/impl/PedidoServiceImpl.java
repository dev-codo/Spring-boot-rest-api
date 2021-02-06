package io.udemy.dougllas.service.impl;

import io.udemy.dougllas.domain.repository.Pedidos;
import io.udemy.dougllas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
