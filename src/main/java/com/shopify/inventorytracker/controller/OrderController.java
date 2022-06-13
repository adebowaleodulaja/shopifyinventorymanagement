package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String displayAllOrder(Model model) {
        model.addAttribute("listoforders", orderService.getALlOrder());
        return "orders";
    }

}
