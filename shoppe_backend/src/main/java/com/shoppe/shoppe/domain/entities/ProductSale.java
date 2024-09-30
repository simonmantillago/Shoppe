package com.shoppe.shoppe.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "sales_products") 
@Data
public class ProductSale {

    @EmbeddedId
    private ProductSaleId id;

    @ManyToOne
    @MapsId("saleId")
    @ToString.Exclude // Excluir esta propiedad para evitar ciclos
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @MapsId("productId")    
    @ToString.Exclude // Excluir esta propiedad para evitar ciclos
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(columnDefinition = "DECIMAL(16,2)", nullable = false)
    private float total;
    
    @Column(nullable = true)
    private int status;
}
