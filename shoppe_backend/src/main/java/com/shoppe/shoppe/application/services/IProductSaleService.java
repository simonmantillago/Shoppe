package com.shoppe.shoppe.application.services;

import java.util.List;
import java.util.Optional;

import com.shoppe.shoppe.domain.entities.ProductSale;
import com.shoppe.shoppe.domain.entities.ProductSaleId;

public interface IProductSaleService {
    List<ProductSale> findAll();

    Optional<ProductSale> findById(ProductSaleId productSaleId);

    ProductSale save(ProductSale productSale);

    Optional<ProductSale> update(ProductSaleId productSaleId, ProductSale productSale);

    Optional<ProductSale> delete(ProductSaleId productSaleId);
}
