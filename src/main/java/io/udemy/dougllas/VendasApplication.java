package io.udemy.dougllas;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

//    @Autowired
//    @Qualifier("appNameHere")
//    private String appName;

    /* Option with application.properties */
    @Value("${application.name}")
    private String appName;

    @GetMapping("/hello")
    public String olaMundo() {
        return appName;
    }

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
//            Cliente cliente = new Cliente();
//            cliente.setNome("Mingau");
//            clientes.salvar(cliente); // OR...

            System.out.println("-> Salvando");
            clientes.salvar(new Cliente("Mingau"));
            clientes.salvar(new Cliente("TomTom"));

            List<Cliente> allClients = clientes.obterTodos();
            allClients.forEach(System.out::println);

            System.out.println("-> Atualizando");
            allClients.forEach(c -> {
                c.setNome(c.getNome() + " updated.");
                clientes.atualizar(c);
            });
            allClients = clientes.obterTodos();
            allClients.forEach(System.out::println);

            System.out.println("-> Buscando");
            clientes.buscarPorNome("ing").forEach(System.out::println);

//            System.out.println("-> Deletando");
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });

            allClients = clientes.obterTodos();
            if (allClients.isEmpty()) {
                System.out.println("Nenhum cliente encontrado");
            } else {
                System.out.println("Existem clientes!");
                allClients.forEach(System.out::println);            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
