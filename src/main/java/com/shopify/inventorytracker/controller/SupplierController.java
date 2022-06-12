package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.Purchase;
import com.shopify.inventorytracker.model.Supplier;
import com.shopify.inventorytracker.service.PurchasesService;
import com.shopify.inventorytracker.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("/suppliers")
    public String displayAllSuppliers(Model model) {
        model.addAttribute("supplierlist", supplierService.displayAllSuppliers());
        return "supplier";
    }

    @GetMapping("/addnewsupplier")
    public String addNewSupplier(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "addsupplier";
    }

    @PostMapping("/saveSupplier")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier, RedirectAttributes redirectAttributes) {
        if (supplierService.isSupplierExists(supplier)) {
            redirectAttributes.addFlashAttribute("error", "This supplier already exists");
            return "redirect:/addnewsupplier";
        }
        supplierService.saveSupplier(supplier);
        redirectAttributes.addFlashAttribute("success", "New supplier has been added successfully");
        return "redirect:/addnewsupplier";
    }
}
