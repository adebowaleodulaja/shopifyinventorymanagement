package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.DeletedProduct;
import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.service.DeletedProductService;
import com.shopify.inventorytracker.service.ProductService;
import com.shopify.inventorytracker.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return productService.displayAllProducts(model);
    }

    @GetMapping("/addnewproduct")
    public String addNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "addproduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        return productService.saveProduct(product, redirectAttributes);
    }

    @GetMapping("/updateProduct/{id}")
    public String productToBeUpdated(@PathVariable(value = "id") long id, Model model) {
        return productService.getProductToUpdate(id, model);
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("updateproduct") Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product.getSerialNumber(), product.getQuantityReceived(), product.getMinimumTolerance(),
                product.getNotify(), product.getId());
        redirectAttributes.addFlashAttribute("success", "Product was updated successfully");

        return "redirect:/";
    }

    @GetMapping("/deleteProduct/{id}")
    public String productToBeDeleted(@PathVariable(value = "id") long id, Model model) {
        return productService.getProductToDelete(id, model);
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@ModelAttribute("deleteproduct") DeletedProduct deletedProduct, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(deletedProduct, redirectAttributes);
        return "redirect:/";
    }
}
