package com.example.loja.service;

import com.example.loja.dto.ProdutoBusca;
import com.example.loja.dto.ProdutoDTO;
import com.example.loja.models.Produto;
import com.example.loja.repository.ProdutoRepository;
import com.example.loja.repository.specification.ProdutoSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoDTO criarProduto(ProdutoDTO produto) {
        Produto produtoBD = produtoRepository.save(Produto.convert(produto));
        return ProdutoDTO.convert(produtoBD);
    }

    public Page<ProdutoDTO> listAll(Pageable pageable) {
        return produtoRepository
                .findAll(pageable)
                .map(ProdutoDTO::convert);
    }

    public List<ProdutoDTO> specification(ProdutoBusca produtoBusca) {
        Specification<Produto> specification = Specification.where(null);

        if (produtoBusca.getCode() != null) {
            specification = createSpecification(specification, ProdutoSpecification.filterByCode(produtoBusca.getCode()));
        }
        return produtoRepository.findAll(specification).stream().map(ProdutoDTO::convert).collect(Collectors.toList());
    }

    private Specification<Produto> createSpecification(Specification<Produto> specification, Specification<Produto> filterByCode) {

        return specification.and(filterByCode);
    }
}
