package com.shopify.inventorytracker.controller;

import com.shopify.inventorytracker.model.Order;
import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.service.OrderService;
import com.shopify.inventorytracker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @GetMapping("/orders")
    public String displayAllOrder(Model model) {
        model.addAttribute("listoforders", orderService.getAllOrder());
        return "orders";
    }

    @GetMapping("/addneworder")
    public String addNewOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("listOfProduct", productService.displayAllProducts());
        return "addorder";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order, RedirectAttributes redirectAttributes) {
        long productId = order.getProduct().getId();
        Optional<Product> getProduct = productService.findProduct(productId);
        if (getProduct.isPresent()) {
            Product product = getProduct.get();
            int quantityLeft = product.getQuantityLeft();
            int updatedQuantityLeft = quantityLeft - order.getOrderQuantity();

            if (quantityLeft < order.getOrderQuantity()) {
                redirectAttributes.addFlashAttribute("error", "This product have only " + quantityLeft + " item(s) left in stock");
                return "redirect:/addneworder";
            }

            productService.updateQuantityLeft(updatedQuantityLeft, productId);
            orderService.saveOrder(order);
            redirectAttributes.addFlashAttribute("success", "New order has been placed");
        } else {
            redirectAttributes.addFlashAttribute("error", "An unknown error occurred");
        }

        return "redirect:/addneworder";
    }

}
