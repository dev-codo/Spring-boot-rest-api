package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.repository.Pedidos;
import io.udemy.dougllas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

//    @Autowired // OR...
    private PedidoService service;

    /* ...this. Better for unit tests */
    public PedidoController(PedidoService service) {
        this.service = service;
    }


}
