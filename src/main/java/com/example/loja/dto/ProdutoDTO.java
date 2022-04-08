package com.example.loja.dto;


import com.example.loja.models.Produto;
import com.example.loja.models.ProdutoCompra;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Integer id;
    private String code;
    private Long price;
    private int units;

    public static ProdutoDTO convert(ProdutoCompra produtoCompra){
        ProdutoDTO dto = new ProdutoDTO();
        dto.setCode(produtoCompra.getProduto().getCode());
        dto.setPrice(produtoCompra.getProduto().getPrice());
        dto.setUnits(produtoCompra.getProduto().getUnits());
        dto.setId(produtoCompra.getProduto().getId());
        return dto;
    }

    public static ProdutoDTO convert(Produto produto){
        ProdutoDTO dto = new ProdutoDTO();
        dto.setCode(produto.getCode());
        dto.setPrice(produto.getPrice());
        dto.setUnits(produto.getUnits());
        dto.setId(produto.getId());
        return dto;
    }

}
