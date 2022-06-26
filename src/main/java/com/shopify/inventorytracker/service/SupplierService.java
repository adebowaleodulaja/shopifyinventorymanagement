package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.Supplier;
import com.shopify.inventorytracker.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public List<Supplier> displayAllSuppliers() {
        return supplierRepository.findAll();
    }

    public boolean isSupplierExists(Supplier supplier) {
        Supplier findSupplier = supplierRepository.findByName(supplier.getName());

        return findSupplier != null;
    }

}
