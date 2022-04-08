package com.example.loja.repository;

import com.example.loja.models.Compra;
import com.example.loja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>, JpaSpecificationExecutor<Compra> {

    List<Compra> findByDate(LocalDateTime date);

    List<Compra> findByTaxpayerId(String taxpayerId);

    List<Compra> findByTotal(Float total);


}
