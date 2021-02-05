package io.udemy.dougllas;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.entity.Pedido;
import io.udemy.dougllas.domain.repository.Clientes;
import io.udemy.dougllas.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
//@RestController
public class VendasApplication {

    /* Substituido pelo metodo POST */
//    @Bean
//    public CommandLineRunner cmdLinerunner(@Autowired Clientes clientes) {
//        return args -> {
//            Cliente c = new Cliente(null, "Fulano");
//            clientes.save(c);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


////    @Autowired
////    @Qualifier("appNameHere")
////    private String appName;
//
//    /* Option with application.properties */
//    @Value("${application.name}")
//    private String appName;
//
//    @GetMapping("/hello")
//    public String olaMundo() {
//        return appName;
//    }
//
//    @Bean
//    public CommandLineRunner init(
//            @Autowired Clientes clientes,
//            @Autowired Pedidos pedidos
//    ) {
//        return args -> {
////            Cliente cliente = new Cliente();
////            cliente.setNome("Mingau");
////            clientes.salvar(cliente); // OR...
//
//            System.out.println("-> Salvando");
////            clientes.save(new Cliente("Mingau"));
////            clientes.save(new Cliente("TomTom"));
//            Cliente fulano = new Cliente("Fulano");
//            clientes.save(fulano);
//
//            Pedido p = new Pedido();
//            p.setCliente(fulano);
//            p.setDataPedido(LocalDate.now());
//            p.setTotal(BigDecimal.valueOf(100));
//            pedidos.save(p);
//
////            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
////            System.out.println(cliente);
////            System.out.println(cliente.getPedidos());
//
//            pedidos.findByCliente(fulano).forEach(System.out::println );
//
////            List<Cliente> allClients = clientes.findAll();
////            allClients.forEach(System.out::println);
////
////            boolean existe = clientes.existsByNome("asdf");
////            System.out.println(existe);
//
////            List<Cliente> byNome = clientes.encontrarPorNome("Mingau");
////            byNome.forEach(System.out::println);
//
////            System.out.println("-> Atualizando");
////            allClients.forEach(c -> {
////                c.setNome(c.getNome() + " updated.");
////                clientes.save(c);
////            });
////            allClients = clientes.findAll();
////            allClients.forEach(System.out::println);
////
////            System.out.println("-> Buscando");
////            clientes.findByNomeLike("%ing%").forEach(System.out::println);
////
////            System.out.println("-> Deletando");
////            clientes.findAll().forEach(c -> {
////                clientes.delete(c);
////            });
////
////            allClients = clientes.findAll();
////            if (allClients.isEmpty()) {
////                System.out.println("Nenhum cliente encontrado");
////            } else {
////                System.out.println("Existem clientes!");
////                allClients.forEach(System.out::println);
////            }
//        };
//    }

}
