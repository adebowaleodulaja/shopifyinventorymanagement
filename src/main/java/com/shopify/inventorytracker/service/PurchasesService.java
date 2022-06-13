package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.Purchase;
import com.shopify.inventorytracker.repository.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasesService {
    @Autowired
    private PurchasesRepository purchasesRepository;

    public void savePurchase(Purchase purchase) {
        purchasesRepository.save(purchase);
    }

    public List<Purchase> displayAllPurchases() {
        return purchasesRepository.findAll();
    }

    public void deletePurchase(long id) {
        purchasesRepository.deleteById(id);
    }

}
