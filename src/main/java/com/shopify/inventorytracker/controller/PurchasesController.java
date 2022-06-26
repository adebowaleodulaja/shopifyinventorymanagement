package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.Purchase;
import com.shopify.inventorytracker.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PurchasesController {

    @Autowired
    PurchasesService purchasesService;

    @GetMapping("/purchases")
    public String displayAllPurchases(Model model) {
        model.addAttribute("purchaselist", purchasesService.displayAllPurchases());
        return "purchase";
    }

    @GetMapping("/addnewpurchase")
    public String addNewPurchase(Model model) {
        purchasesService.addPurchase(model);
        return "addpurchase";
    }

    @PostMapping("/savePurchase")
    public String saveProduct(@ModelAttribute("purchase") Purchase purchase, RedirectAttributes redirectAttributes) {
        purchasesService.savePurchase(purchase, redirectAttributes);
        return "redirect:/addnewpurchase";
    }
}
