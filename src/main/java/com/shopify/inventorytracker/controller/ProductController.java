package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.DeletedProduct;
import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.repository.PurchasesRepository;
import com.shopify.inventorytracker.service.DeletedProductService;
import com.shopify.inventorytracker.service.ProductService;
import com.shopify.inventorytracker.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    DeletedProductService deletedProductService;

    @Autowired
    PurchasesService purchasesService;

    @GetMapping("/")
    public String displayAllProducts(Model model) {
        model.addAttribute("listOfProducts", productService.displayAllProducts());
        return "home";
    }

    @GetMapping("/addnewproduct")
    public String addNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "addproduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        String feedbackMessage = productService.checkProductName(product);
        product.setQuantityLeft(product.getQuantityReceived());

        if (feedbackMessage.contains("Product name")) {
            redirectAttributes.addFlashAttribute("error", feedbackMessage);
            return "redirect:/addnewproduct";
        }

        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("success", feedbackMessage);

        return "redirect:/addnewproduct";

    }

    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable(value = "id") long id, Model model) {
        Optional<Product> findProduct = productService.findProduct(id);
        if (findProduct.isPresent()) {
            Product product = findProduct.get();
            model.addAttribute("updateproduct", product);
            return "updateproduct";
        }

        return "";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("updateproduct") Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product.getSerialNumber(), product.getQuantityReceived(), product.getMinimumTolerance(),
                product.getNotify(), product.getId());
        redirectAttributes.addFlashAttribute("success", "Product was updated successfully");

        return "redirect:/";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id, Model model) {
        Optional<Product> findProduct = productService.findProduct(id);
        if (findProduct.isPresent()) {
            Product product = findProduct.get();
            DeletedProduct deletedProduct = new DeletedProduct();
            model.addAttribute("productodelte", product);
            model.addAttribute("deleteproduct", deletedProduct);
            return "deleteproduct";
        }

        return "";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@ModelAttribute("deleteproduct") DeletedProduct deletedProduct, RedirectAttributes redirectAttributes) {
        Optional<Product> getProduct = productService.findProduct(deletedProduct.getId());
        int totalOrdered = 0, quantityLeft = 0;
        if (getProduct.isPresent()) {
            Product product = getProduct.get();
            totalOrdered = product.getTotalOrdered();
            quantityLeft = product.getQuantityLeft();
        }
        purchasesService.deletePurchase(deletedProduct.getId());
        productService.deleteProduct(deletedProduct.getId());

        deletedProduct.setTotalOrdered(totalOrdered);
        deletedProduct.setQuantityLeft(quantityLeft);
        deletedProductService.saveDeletedProduct(deletedProduct);
        redirectAttributes.addFlashAttribute("success", "Product has been deleted.");
        return "redirect:/";
    }
}
