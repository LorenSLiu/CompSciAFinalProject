package com.application.description;

import java.util.ArrayList;

public interface ProductBehavior {
    int getTotalInventory();
    ArrayList<Product> GetAllProducts();
    ArrayList<Product> GetProducts(String ProductName);
}