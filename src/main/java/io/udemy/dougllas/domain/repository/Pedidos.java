package io.udemy.dougllas.domain.repository;

import io.udemy.dougllas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
