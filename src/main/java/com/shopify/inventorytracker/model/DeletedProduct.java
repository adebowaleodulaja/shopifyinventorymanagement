package com.shopify.inventorytracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "deletedproduct")
public class DeletedProduct {

    @Id
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
    @Column(name = "comments")
    private String comment;

    public DeletedProduct() {
    }

    public DeletedProduct(long id, String name, String serialNumber, Integer quantityReceived, int quantityLeft, int totalOrdered, Integer minimumTolerance, String notify, String comment) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.quantityReceived = quantityReceived;
        this.quantityLeft = quantityLeft;
        this.totalOrdered = totalOrdered;
        this.minimumTolerance = minimumTolerance;
        this.notify = notify;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedProduct that = (DeletedProduct) o;
        return id == that.id && quantityLeft == that.quantityLeft && totalOrdered == that.totalOrdered && Objects.equals(name, that.name) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(quantityReceived, that.quantityReceived) && Objects.equals(minimumTolerance, that.minimumTolerance) && Objects.equals(notify, that.notify) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, serialNumber, quantityReceived, quantityLeft, totalOrdered, minimumTolerance, notify, comment);
    }

    @Override
    public String toString() {
        return "DeletedProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", quantityReceived=" + quantityReceived +
                ", quantityLeft=" + quantityLeft +
                ", totalOrdered=" + totalOrdered +
                ", minimumTolerance=" + minimumTolerance +
                ", notify='" + notify + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
