package com.shoppe.shoppe.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "sales_products") 
@Data
public class ProductSale {

    @EmbeddedId
    private ProductSaleId id;

    @ManyToOne
    @JoinColumn(name = "sale_id",insertable=false, updatable=false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(columnDefinition = "DECIMAL(16,2)", nullable = false)
    private float total;
    
    @Column(nullable = true)
    private int status;
}
