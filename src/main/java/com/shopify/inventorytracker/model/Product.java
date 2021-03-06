package com.shopify.inventorytracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(name = "UniqueNameAndSerialNumber", columnNames = {"name", "serialnumber"})})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "serialnumber")
    private String serialNumber;
    @Column(name = "quantityreceived")
    private Integer quantityReceived;
    @Column(name = "quantityleft")
    private int quantityLeft;
    @Column(name = "totalordered")
    private int totalOrdered;
    @Column(name = "minimumtolerance")
    private Integer minimumTolerance;
    @Column(name = "notify")
    private String notify;

    public Product() {
    }

    public Product(long id, String name, String serialNumber, int minimumTolerance, String notify) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.minimumTolerance = minimumTolerance;
        this.notify = notify;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public int getTotalOrdered() {
        return totalOrdered;
    }

    public void setTotalOrdered(int totalOrdered) {
        this.totalOrdered = totalOrdered;
    }

    public Integer getMinimumTolerance() {
        return minimumTolerance;
    }

    public void setMinimumTolerance(Integer minimumTolerance) {
        this.minimumTolerance = minimumTolerance;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && quantityLeft == product.quantityLeft && totalOrdered == product.totalOrdered && Objects.equals(name, product.name) && Objects.equals(serialNumber, product.serialNumber) && Objects.equals(quantityReceived, product.quantityReceived) && Objects.equals(minimumTolerance, product.minimumTolerance) && Objects.equals(notify, product.notify);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, serialNumber, quantityReceived, quantityLeft, totalOrdered, minimumTolerance, notify);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", quantityReceived=" + quantityReceived +
                ", quantityLeft=" + quantityLeft +
                ", totalOrdered=" + totalOrdered +
                ", minimumTolerance=" + minimumTolerance +
                ", notify='" + notify + '\'' +
                '}';
    }
}
