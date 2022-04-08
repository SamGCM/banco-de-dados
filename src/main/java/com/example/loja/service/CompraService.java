package com.example.loja.service;


import com.example.loja.dto.CompraBusca;
import com.example.loja.dto.CompraDTO;
import com.example.loja.dto.ProdutoDTO;
import com.example.loja.models.Compra;
import com.example.loja.models.Produto;
import com.example.loja.models.ProdutoCompra;
import com.example.loja.models.ProdutoCompraKey;
import com.example.loja.repository.CompraRepository;
import com.example.loja.repository.ProdutoCompraRepository;
import com.example.loja.repository.ProdutoRepository;
import com.example.loja.repository.specification.CompraSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoCompraRepository produtoCompraRepository;

    public Page<CompraDTO> listAll(Pageable pageable) {
        return compraRepository
                .findAll(pageable)
                .map(CompraDTO::convert);
    }

    public CompraDTO criarCompra(CompraDTO compraDTO) {
        Compra compra = new Compra();
        compra.setDate(LocalDateTime.now());
        compra.setTaxpayerId(compraDTO.getTaxpayerId());

        for(ProdutoDTO produto : compraDTO.getListProducts()){
            Integer id = produto.getId();
            Integer unitsProductActual = produtoRepository.getById(id).getUnits();
            if(unitsProductActual >= produto.getUnits()) {
                compra.setTotal(compra.getTotal() + produto.getPrice());

            } else {
                ResponseStatusException responseStatusException = new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto de id " + produto.getId() + " sem estoque"
                );
                throw responseStatusException;
            }

        }

        compra = compraRepository.save(compra);

        for(ProdutoDTO produto: compraDTO.getListProducts()) {
            ProdutoCompraKey key = new ProdutoCompraKey();
            ProdutoCompra produtoCompra = new ProdutoCompra();

            key.setIdCompra(compra.getId());
            key.setIdProduto(produto.getId());

            produtoCompra.setCompra(compra);
            produtoCompra.setProduto(Produto.convert(produto));
            produtoCompra.setProdutoCompraKey(key);
            compra.getListProducts().add(produtoCompraRepository.save(produtoCompra));
        }


        return CompraDTO.convert(compra);
    }

    public List<CompraDTO> specification(CompraBusca compraBusca) {
        Specification<Compra> specification = Specification.where(null);

        if (compraBusca.getTaxpayerId() != null) {
            specification = createSpecification(specification, CompraSpecification.filterByTaxpayerId(compraBusca.getTaxpayerId()));
        }
        return compraRepository.findAll(specification).stream().map(CompraDTO::convert).collect(Collectors.toList());
    }

    private Specification<Compra> createSpecification(Specification<Compra> specification, Specification<Compra> filterByTaxpayerId) {

        return specification.and(filterByTaxpayerId);
    }


}
