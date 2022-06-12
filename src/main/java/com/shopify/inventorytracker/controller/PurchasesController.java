package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.model.Purchase;
import com.shopify.inventorytracker.service.ProductService;
import com.shopify.inventorytracker.service.PurchasesService;
import com.shopify.inventorytracker.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PurchasesController {

    @Autowired
    PurchasesService purchasesService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    ProductService productService;


    @GetMapping("/purchases")
    public String displayAllPurchases(Model model) {
        model.addAttribute("purchaselist", purchasesService.displayAllPurchases());
        return "purchase";
    }

    @GetMapping("/addnewpurchase")
    public String addNewPurchase(Model model) {
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        model.addAttribute("suppliersOption", supplierService.displayAllSuppliers());
        model.addAttribute("productOption", productService.displayAllProducts());
        return "addpurchase";
    }

    @PostMapping("/savePurchase")
    public String saveProduct(@ModelAttribute("purchase") Purchase purchase, RedirectAttributes redirectAttributes) {
        long productId = purchase.getProduct().getId();
        Optional<Product> getProduct = productService.findProduct(productId);
        if (getProduct.isPresent()) {
            Product product = getProduct.get();
            int quantityReceived = product.getQuantityReceived();
            int updatedQuantity = purchase.getItemCount() + quantityReceived;

            productService.updateQuantityReceived(updatedQuantity, productId);
            purchasesService.savePurchase(purchase);
            redirectAttributes.addFlashAttribute("success", "New purchase has been added successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "An unknown error occurred");
        }

        return "redirect:/addnewpurchase";
    }
}
