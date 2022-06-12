package com.shopify.inventorytracker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customerfirstname")
    private String customerFirstName;
    @Column(name = "customerlastname")
    private String customerLastName;
    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;
    @Column(name = "orderamount")
    private Integer orderAmount;
    @Column(name = "orderdate")
    private String orderDate;

    public Order() {
    }

    public Order(int id, String customerFirstName, String customerLastName, Product product, Integer orderAmount, String orderDate) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.product = product;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(customerFirstName, order.customerFirstName) && Objects.equals(customerLastName, order.customerLastName) && Objects.equals(product, order.product) && Objects.equals(orderAmount, order.orderAmount) && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerFirstName, customerLastName, product, orderAmount, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", productId=" + product +
                ", orderAmount=" + orderAmount +
                ", orderDate=" + orderDate +
                '}';
    }
}
