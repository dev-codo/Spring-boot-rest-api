package io.udemy.dougllas.domain.repository;

import io.udemy.dougllas.domain.entity.Cliente;
import io.udemy.dougllas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

//  Optional<Pedido> findByIdFetchItens(@Param("id") Integer id); // this Query method doesn't work. See next one.

    @Query("select p from Pedido p left join fetch p.itens where p.id = :id")
    Optional<Pedido> acharItemPorId(@Param("id") Integer id);

}
