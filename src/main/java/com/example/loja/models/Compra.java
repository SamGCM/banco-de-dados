package com.example.loja.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name="compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "bu_date")
    private LocalDateTime date;

    @Column(name= "bu_taxpayer_id")
    private String taxpayerId;

    @Column(name= "bu_total")
    private long total;

    @OneToMany(mappedBy = "compra")
    private List<ProdutoCompra> listProducts = new ArrayList<>();
}
