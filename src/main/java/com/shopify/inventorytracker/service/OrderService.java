package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.Order;
import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    public void addOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("listOfProduct", productService.displayListOfProducts());
    }

    public String saveOrder(Order order, RedirectAttributes redirectAttributes) {
        long productId = order.getProduct().getId();
        Optional<Product> getProduct = productService.findProduct(productId);
        if (getProduct.isPresent()) {
            Product product = getProduct.get();
            int quantityLeft = product.getQuantityLeft();
            int totalOrder = product.getTotalOrdered();
            int updatedQuantityLeft = quantityLeft - order.getOrderQuantity();
            int updateTotalOrder = totalOrder + order.getOrderQuantity();

            if (quantityLeft < order.getOrderQuantity()) {
                redirectAttributes.addFlashAttribute("error", "This product have only " + quantityLeft + " item(s) left in stock");
                return "redirect:/addneworder";
            }

            productService.updateQuantityLeft(updatedQuantityLeft, productId);
            productService.updateUpdateTotalOrder(updateTotalOrder, productId);
            orderRepository.save(order);
            redirectAttributes.addFlashAttribute("success", "New order has been placed");
        } else {
            redirectAttributes.addFlashAttribute("error", "An unknown error occurred");
        }

        return "redirect:/addneworder";
    }
}
