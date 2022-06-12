package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

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
}
