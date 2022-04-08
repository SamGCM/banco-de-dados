package com.example.loja.repository;

import com.example.loja.models.ProdutoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoCompraRepository extends JpaRepository<ProdutoCompra, Integer>, JpaSpecificationExecutor<ProdutoCompra> {
}
