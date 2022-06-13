package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.DeletedProduct;
import com.shopify.inventorytracker.repository.DeletedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletedProductService {
    @Autowired
    private DeletedProductRepository deletedProductRepository;

    public void saveDeletedProduct(DeletedProduct deletedProduct) {
        if (deletedProduct != null)
            deletedProductRepository.save(deletedProduct);
    }
}
