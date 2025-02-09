package io.github.kayquesanmartin.controle_estoqueapi.repository;

import io.github.kayquesanmartin.controle_estoqueapi.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produtos, UUID> {
}
