package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired // OR...
    private Clientes clientes;

//    ...this
//    public ClienteController(Clientes clientes) {
//        this.clientes = clientes;
//    }

    @GetMapping
    public List<Cliente> getAllClientes(Cliente cliente) {
//        List<Cliente> clienteAll = clientes.findAll();
//
//        if (clienteAll.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return new ResponseEntity<>(clienteAll, HttpStatus.OK);

        return clientes.findAll();
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado")); // http 404
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // http 201
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // http 204
    public void deletar(@PathVariable Integer id) {
        clientes.findById(id)
                .map(cl -> {
                    clientes.delete(cl);
                    return cl;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updater(@PathVariable Integer id, @RequestBody Cliente cliente) {
        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    /* Search/filter a cliente. Work as findAll as well, if no query params is entered in Postman */
    @GetMapping("/search")
    public List<Cliente> procurar(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

}
