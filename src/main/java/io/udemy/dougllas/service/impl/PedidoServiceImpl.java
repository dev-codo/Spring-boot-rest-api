package io.udemy.dougllas.service.impl;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.entity.ItemPedido;
import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.domain.entity.Produto;
import io.udemy.dougllas.domain.repository.ClientesRepository;
import io.udemy.dougllas.domain.repository.ItemsPedidoRepository;
import io.udemy.dougllas.domain.repository.PedidosRepository;
import io.udemy.dougllas.domain.repository.ProdutosRepository;
import io.udemy.dougllas.exception.RegraNegocioException;
import io.udemy.dougllas.rest.dto.ItemPedidoDTO;
import io.udemy.dougllas.rest.dto.PedidoDTO;
import io.udemy.dougllas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service // SpringBoot link a Interface com o Impl
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepo;
    private final ClientesRepository clientesRepo;
    private final ProdutosRepository produtosRepo;
    private final ItemsPedidoRepository itemsPedidoRepo;

    /* replace by @RequiredArgsConstructor (lombok) */
//    public PedidoServiceImpl(PedidosRepository pedidosRepo, ClientesRepository clientesRepo, ProdutosRepository produtosRepo, ItemsPedidoRepository itemsPedidoRepo) {
//        this.pedidosRepo = pedidosRepo;
//        this.clientesRepo = clientesRepo;
//        this.produtosRepo = produtosRepo;
//        this.itemsPedidoRepo = itemsPedidoRepo;
//    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente(); // localizar id do Cliente
        Cliente cliente = clientesRepo // uma vez localizado o id, obter o objeto inteiro
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente invalido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente); // ate aqui, Pedido ainda nao esta salvo na DB
        pedidosRepo.save(pedido);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        itemsPedidoRepo.saveAll(itensPedido);
        pedido.setItens(itensPedido);

        return pedido;
    }

    /* converter itensDto em ItemPedido */
    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itensDto) {
        if (itensDto.isEmpty()) {
            throw new RegraNegocioException("Nao eh possível realizar um pedido sem itens.");
        }

        return itensDto
                .stream() /* tipo RxJava */
                .map(dto -> {
                    Integer idProduto = dto.getProduto(); // localizar id do Produto
                    Produto produto = produtosRepo // uma vez localizado o id, obter o objeto inteiro
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Cod. de produto invalido: " + idProduto)
                            );

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
