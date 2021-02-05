package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired // OR...
    private Clientes clientes;

//    ...this
//    public ClienteController(Clientes clientes) {
//        this.clientes = clientes;
//    }

    @GetMapping
    public ResponseEntity getAllClientes(Cliente cliente) {
        List<Cliente> clienteAll = clientes.findAll();

        if (clienteAll.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(clienteAll, HttpStatus.OK);
    }

    @GetMapping("{id}")
//    @ResponseBody //not really necessary
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

//        return ResponseEntity.notFound().build(); OR...
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
//    @ResponseBody //not really necessary
    public ResponseEntity salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {

        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
//            return ResponseEntity.noContent().build(); ...OR
            return new ResponseEntity(HttpStatus.NO_CONTENT); // http 204
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity updater(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }).orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

}
