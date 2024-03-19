package com.application.description;

import jakarta.validation.constraints.NotNull;

public class Product {
    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private String location;
    @NotNull
    private int numberInStore;
    @NotNull
    private String productId;
    @NotNull
    private String imageUrl;
    public Product(String name, double price, String location, int numberInStore) {
        this.price = price;
        this.location = location;
        this.name = name;
        this.numberInStore = numberInStore;
    }

    public Product(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberInStore() {
        return numberInStore;
    }

    public void setNumberInStore(int numberInStore) {
        this.numberInStore = numberInStore;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", numberInStore=" + numberInStore +
                ", productId='" + productId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
