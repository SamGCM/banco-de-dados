package com.example.loja.controller;

import com.example.loja.dto.ProdutoBusca;
import com.example.loja.dto.ProdutoDTO;
import com.example.loja.models.Produto;
import com.example.loja.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/")
    public Page<ProdutoDTO> listProdutos(Pageable pageable){
        return produtoService.listAll(pageable);
    }

    @GetMapping("/busca")
    public List<ProdutoDTO> buscarProduto(
            ProdutoBusca dto) {
        return produtoService.specification(dto);
    }

    @PostMapping("/")
    public ProdutoDTO criaProduto(@RequestBody ProdutoDTO produto){
        return produtoService.criarProduto(produto);
    }


}
