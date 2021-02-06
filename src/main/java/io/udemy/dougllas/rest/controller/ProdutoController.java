package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.domain.entity.Produto;
import io.udemy.dougllas.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private Produtos produtosRepo;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtosRepo.findAll();
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtosRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto saveProduto(@RequestBody Produto produto) {
        return produtosRepo.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        produtosRepo.findById(id)
                .map(prd -> {
                    produtosRepo.delete(prd); //OR...
//                    produtosRepo.deleteById(id);
                    return Void.TYPE; // alternative to return null. Doesn't need to return anything, actually.
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updater(@PathVariable Integer id, @RequestBody Produto produto) {
        produtosRepo
                .findById(id)
                .map(prdExist -> {
                    produto.setId(prdExist.getId());
                    produtosRepo.save(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public List<Pedido> procurar(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtosRepo.findAll(example);
    }

}
