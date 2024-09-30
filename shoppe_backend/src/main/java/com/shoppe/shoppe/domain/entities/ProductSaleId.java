package com.shoppe.shoppe.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Data
public class ProductSaleId implements Serializable  {

    private Long saleId;
    private Long productId;

    public ProductSaleId() {}

}
