package io.udemy.dougllas.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

//    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET) // OR...
    @GetMapping(value = {"/hello/{nome}", "/alo/{nome}"})
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }
}
