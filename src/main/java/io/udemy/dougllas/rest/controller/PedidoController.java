package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.ItemPedido;
import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.domain.enums.StatusPedido;
import io.udemy.dougllas.rest.dto.AtualizacaoStatusPedidoDTO;
import io.udemy.dougllas.rest.dto.InfoItemPedidoDTO;
import io.udemy.dougllas.rest.dto.InfoPedidoDTO;
import io.udemy.dougllas.rest.dto.PedidoDTO;
import io.udemy.dougllas.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

//    @Autowired // OR...
    private PedidoService pedidoService;

    /* ...this. Better for unit tests */
    public PedidoController(PedidoService service) {
        this.pedidoService = service;
    }

    /* retornar Cod. (Integer) Pedido, qdo tiver criado um Pedido */
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId(); // in Postman: will return in response body with the pedido Id.
    }
//--------------------------------------------------------------------------------------------
    /* consultar um Pedido pelo id */
    @GetMapping("{id}")
    public InfoPedidoDTO getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id) // Optional | se achar: map, se nao: orElseThrow
                .map(p -> converterPedido(p)) // Pedido convertido para DTO (client-side)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido nao encontrado"));
    }

    private InfoPedidoDTO converterPedido(Pedido pedido) {
       return InfoPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItemPedido(pedido.getItens()))
                .build();
    }

    private List<InfoItemPedidoDTO> converterItemPedido(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens
                .stream()
                .map(
                    item -> InfoItemPedidoDTO
                                .builder()
                                .descricaoProduto(item.getProduto().getDescricao())
                                .precoUnitario(item.getProduto().getPreco())
                                .quantidade(item.getQuantidade())
                                .build()
                ).collect(Collectors.toList());
    }
//--------------------------------------------------------------------------------------------
     /* PUT/PATCH is a mix of DELETE method with POST */
    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT) // 204
    public void updateStatus(@PathVariable Integer id, // informado na Url
                             @RequestBody AtualizacaoStatusPedidoDTO dto) { // informado no Body da requisicao (
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

}
