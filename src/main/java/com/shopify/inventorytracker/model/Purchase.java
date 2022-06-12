package com.shopify.inventorytracker.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "supplierid", referencedColumnName = "id")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;
    @Column(name = "itemcount")
    private Integer itemCount;
    @Column(name = "purchasedate")
    private String purchaseDate;

    public Purchase() {
        /*LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        purchaseDate = now.format(format);*/
    }

    public Purchase(int id, Supplier supplier, Product product, Integer itemCount, String purchaseDate) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
        this.itemCount = itemCount;
        this.purchaseDate = purchaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id && Objects.equals(supplier, purchase.supplier) && Objects.equals(product, purchase.product) && Objects.equals(itemCount, purchase.itemCount) && Objects.equals(purchaseDate, purchase.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supplier, product, itemCount, purchaseDate);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", supplier=" + supplier +
                ", product=" + product +
                ", itemCount=" + itemCount +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
