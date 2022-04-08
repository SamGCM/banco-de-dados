package com.example.loja.repository.specification;

import com.example.loja.models.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecification {
    public static Specification<Produto> filterByCode(String code) {
        return (root, query, builder) ->
                builder.equal(root.get("code"), code);
    }
}
