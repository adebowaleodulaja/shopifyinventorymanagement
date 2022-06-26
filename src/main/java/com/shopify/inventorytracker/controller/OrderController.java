package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.Order;
import com.shopify.inventorytracker.service.OrderService;
import com.shopify.inventorytracker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String displayAllOrder(Model model) {
        model.addAttribute("listoforders", orderService.getAllOrder());
        return "orders";
    }

    @GetMapping("/addneworder")
    public String addNewOrder(Model model) {
        orderService.addOrder(model);
        return "addorder";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order, RedirectAttributes redirectAttributes) {
        return orderService.saveOrder(order, redirectAttributes);
    }

}
