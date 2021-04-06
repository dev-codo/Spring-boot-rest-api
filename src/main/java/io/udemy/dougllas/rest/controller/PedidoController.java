package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.rest.dto.PedidoDTO;
import io.udemy.dougllas.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

//    @Autowired // OR...
    private PedidoService service;

    /* ...this. Better for unit tests */
    public PedidoController(PedidoService service) {
        this.service = service;
    }

    /* retornar Cod. (Integer) Pedido */
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

}
