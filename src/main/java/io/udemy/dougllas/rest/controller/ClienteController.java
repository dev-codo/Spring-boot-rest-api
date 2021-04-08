package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.repository.ClientesRepository;
import io.udemy.dougllas.rest.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController  implements RestInterface<Cliente> {

    @Autowired // OR...
    private ClientesRepository clientesRepo;

//    ...this
//    public ClienteController(Clientes clientes) {
//        this.clientes = clientes;
//    }

//    @GetMapping
//    public List<Cliente> getAllClientes() {
////        List<Cliente> clienteAll = clientes.findAll();
////
////        if (clienteAll.isEmpty()) {
////            return ResponseEntity.notFound().build();
////        }
////
////        return new ResponseEntity<>(clienteAll, HttpStatus.OK);
//
//        return clientesRepo.findAll();
//    }

//    @GetMapping("{id}")
//    public Cliente getClienteById(@PathVariable Integer id) {
//        return clientesRepo
//                .findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado")); // http 404
//    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) // http 201
//    public Cliente salvar(@RequestBody Cliente cliente) {
//        return clientesRepo.save(cliente);
//    }

//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT) // http 204
//    public void deletar(@PathVariable Integer id) {
//        clientesRepo.findById(id)
//                .map(cl -> {
//                    clientesRepo.delete(cl);
//                    return cl;
//                })
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
//    }

//    /* PUT is a mix of DELETE method with POST */
//    @PutMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updater(@PathVariable Integer id, @RequestBody Cliente cliente) {
//        clientesRepo
//                .findById(id)
//                .map(clienteExistente -> {
//                    cliente.setId(clienteExistente.getId());
//                    clientesRepo.save(cliente);
//                    return clienteExistente;
//                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
//    }

//    /* Search/filter a cliente. Work as findAll as well, if no query params is entered in Postman */
//    @GetMapping("/search")
//    public List<Cliente> procurar(Cliente filtro) {
//        ExampleMatcher matcher = ExampleMatcher
//                                    .matching()
//                                    .withIgnoreCase()
//                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//
//        Example example = Example.of(filtro, matcher);
//        return clientesRepo.findAll(example);
//    }

//    --------------------- Interface criada e implementada a partir da sugestao no v.44 ----------------------
    @GetMapping
    @Override
    public List<Cliente> getAll() {
        return clientesRepo.findAll();
    }

    @GetMapping("{id}")
    @Override
    public Cliente getById(@PathVariable Integer id) {
        return clientesRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado")); // http 404
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // http 201
    @Override
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {
        return clientesRepo.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // http 204
    @Override
    public void deletar(@PathVariable Integer id) {
        clientesRepo
                .findById(id)
                .map(cl -> {
                    clientesRepo.delete(cl);
                    return cl;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void updater(@PathVariable Integer id,
                        @RequestBody @Valid Cliente cliente) {
        clientesRepo
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepo.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @GetMapping("/search")
    @Override
    public List<Cliente> procurar(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientesRepo.findAll(example);
    }


}
