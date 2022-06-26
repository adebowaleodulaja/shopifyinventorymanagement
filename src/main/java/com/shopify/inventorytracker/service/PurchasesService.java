package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.model.Purchase;
import com.shopify.inventorytracker.repository.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class PurchasesService {
    @Autowired
    private PurchasesRepository purchasesRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    public void savePurchase(Purchase purchase, RedirectAttributes redirectAttributes) {
        long productId = purchase.getProduct().getId();
        Optional<Product> getProduct = productService.findProduct(productId);
        if (getProduct.isPresent()) {
            Product product = getProduct.get();
            int quantityReceived = product.getQuantityReceived();
            int updatedQuantity = purchase.getItemCount() + quantityReceived;

            productService.updateQuantityReceived(updatedQuantity, productId);
            purchasesRepository.save(purchase);
            redirectAttributes.addFlashAttribute("success", "New purchase has been added successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "An unknown error occurred");
        }
    }

    public List<Purchase> displayAllPurchases() {
        return purchasesRepository.findAll();
    }

    public void deletePurchase(long id) {
        purchasesRepository.deleteById(id);
    }

    public void addPurchase(Model model) {
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        model.addAttribute("suppliersOption", supplierService.displayAllSuppliers());
        model.addAttribute("productOption", productService.displayListOfProducts());
    }
}
