package com.database;

import com.application.description.Product;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class BackEndProductsProviderTest {

    @Test
    void getProducts() {
        BackEndProductsProvider backEndProductsProvider = new BackEndProductsProvider();
        assertNotNull(backEndProductsProvider.GetProducts(""));
    }
    @Test
    void sendProduct() {
        BackEndProductsProvider backEndProductsProvider = new BackEndProductsProvider();
        Product product = new Product("testProduct", -9.9, "location", -99);
        ResponseEntity response = backEndProductsProvider.Sender(product);
        String resString = response.getBody().toString();
        assertEquals(true, resString.contains("insertedId"));
    }

    @Test
    void responseEntityToJSON() {
  }

    @Test
    void getDocumentArray() {
  }

    @Test
    void getProduct() {
        BackEndProductsProvider backEndProductsProvider = new BackEndProductsProvider();
        //assertNotNull(backEndProductsProvider.Requesters(""));
    }
}