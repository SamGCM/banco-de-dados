package com.example.loja.repository;

import com.example.loja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {
    List<Produto> findByCode(String code);
    Produto getByCode(String code);
    List<Produto> findByPrice(Long price);

}
