package io.udemy.dougllas.domain.repository;

import io.udemy.dougllas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
