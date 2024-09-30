package com.shoppe.shoppe.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String name;

    @ManyToOne
    private Category category;

    @Column(length = 45, nullable = true, name = "bar_code")
    private String barCode;

    @Column(columnDefinition = "DECIMAL(16,2)", name = "sale_price")
    private float salePrice;
    
    private int stock;

    private int status;

    
}
