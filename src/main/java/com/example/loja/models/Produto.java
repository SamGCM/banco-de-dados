package com.example.loja.models;

import com.example.loja.dto.ProdutoDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name="produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "pr_code")
    private String code;

    @Column(name= "pr_price")
    private Long price;

    @Column(name= "pr_units")
    private int units;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public static Produto convert(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setCode(dto.getCode());
        produto.setPrice(dto.getPrice());
        produto.setUnits(dto.getUnits());
        return produto;
    }
}
