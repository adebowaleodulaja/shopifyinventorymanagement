package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        if (product != null)
            productRepository.save(product);
    }

    public List<Product> displayAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(Long id) {
        return productRepository.findById(id);
    }


    public void updateQuantityReceived(int qntyreceived, long id) {
        productRepository.updateQuantityReceived(qntyreceived, id);
    }

    public void updateQuantityLeft(int qntyleft, long id) {
        productRepository.updateQuantityLeft(qntyleft, id);
    }

    public void updateProduct(String snumber, int qreceived, int mtol, String notify, long id) {
        productRepository.updateProduct(snumber, qreceived, mtol, notify, id);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    public String checkProductName(Product product) {
        String errorMessage = "";
        Product findProductByName = productRepository.findByName(product.getName());

        if (findProductByName == null)
            errorMessage = "New product '".concat(product.getName()).concat("' has been added successfully");
        else if (findProductByName.getName().equalsIgnoreCase(product.getName())) {
            errorMessage = "Product name '".concat(product.getName()).concat("' already exists");
        }

        return errorMessage;
    }
}
