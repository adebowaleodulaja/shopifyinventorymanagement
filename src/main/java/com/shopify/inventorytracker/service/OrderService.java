package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.Order;
import com.shopify.inventorytracker.model.Supplier;
import com.shopify.inventorytracker.repository.OrderRepository;
import com.shopify.inventorytracker.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveSupplier(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getALlOrder() {
        return orderRepository.findAll();
    }

}
