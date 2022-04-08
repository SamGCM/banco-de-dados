package com.example.loja.dto;

import com.example.loja.models.Compra;
import com.example.loja.models.Produto;
import com.example.loja.models.ProdutoCompra;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CompraDTO {
    private String taxpayerId;
    private Long total;
    private List<ProdutoDTO> listProducts = new ArrayList<>();

    public static CompraDTO convert(Compra compra){
        CompraDTO dto = new CompraDTO();
        dto.setTaxpayerId(compra.getTaxpayerId());
        dto.setTotal(compra.getTotal());
        dto.getListProducts().addAll(compra.getListProducts().stream().map(ProdutoDTO::convert).collect(Collectors.toList()));
        return dto;
    }
}
