package com.shoppe.shoppe.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Data
public class ProductSaleId implements Serializable  {
    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "product_id")
    private Long productId;

    public ProductSaleId() {}

}
