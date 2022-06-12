package com.shopify.inventorytracker.repository;

import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchase, Long> {

}
