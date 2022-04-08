package com.example.loja.repository.specification;

import com.example.loja.models.Compra;
import org.springframework.data.jpa.domain.Specification;

public class CompraSpecification {
    public static Specification<Compra> filterByTaxpayerId(String taxpayerId) {
        return (root, query, builder) ->
                builder.equal(root.get("taxpayerId"), taxpayerId);
    }
}
