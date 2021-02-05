package io.udemy.dougllas.rest.controller;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired // OR...
    private Clientes clientes;

//    public ClienteController(Clientes clientes) {
//        this.clientes = clientes;
//    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

//        return ResponseEntity.notFound().build(); OR...
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
