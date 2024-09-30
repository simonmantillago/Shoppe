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
@Table(name = "sales")
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column(columnDefinition = "datetime")
    private String date;

    @Column(columnDefinition = "char(1)", name = "payment_method")
    private int paymentMethod;

    @Column(length = 300, nullable = true)
    private String comment;

    @Column(nullable = true)
    private int status;

    

}
