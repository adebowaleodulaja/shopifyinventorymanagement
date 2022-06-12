package com.shopify.inventorytracker.repository;

import com.shopify.inventorytracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.quantityReceived = :qreceived where p.id = :id")
    void updateQuantityReceived(@Param(value = "qreceived") int qntyreceived, @Param(value = "id") long id);
}