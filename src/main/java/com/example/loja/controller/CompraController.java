package com.example.loja.controller;

import com.example.loja.dto.CompraBusca;
import com.example.loja.dto.CompraDTO;
import com.example.loja.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @GetMapping("/")
    public Page<CompraDTO> listCompras(Pageable pageable){
        return compraService.listAll(pageable);
    }

    @GetMapping("/busca")
    public List<CompraDTO> buscarCompra(
            CompraBusca dto) {
        return compraService.specification(dto);
    }

    @PostMapping("/")
    public CompraDTO criaCompra(@RequestBody CompraDTO compra){
        return compraService.criarCompra(compra);
    }
}
