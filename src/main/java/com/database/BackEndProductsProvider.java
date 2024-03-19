package com.database;

import com.application.description.Product;
import com.application.description.ProductBehavior;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Key.Key;

import static com.database.DBHelper.GetDocumentArray;

@SuppressWarnings("all")
public class BackEndProductsProvider implements ProductBehavior {
    static BackEndProductsProvider backEndProductsProvider = new BackEndProductsProvider();
    static DBHelper dbHelper = new DBHelper();



    //enter for test only
    public static void main(String[] args) {
//        BackEndProductsProvider backEndProductsProvider = new BackEndProductsProvider();
//        Product product = new Product("testProduct", -9.9, "location", -99);
//        ResponseEntity response = backEndProductsProvider.Sender(product);
//        String resString = response.getBody().toString();
        backEndProductsProvider.DeleteProduct("testProduct");
        //System.out.println(BackEndProductsProvider.ResponseEntityToJSON(BackEndProductsProvider.GetProduct("")));
    }
    @Override
    public int getTotalInventory(){
        ArrayList<Product> products = new ArrayList<>();
        products = GetAllProducts();
        int total = 0;
        for(int i=0;i<products.size();i++){
            total += products.get(i).getNumberInStore();
        }
        return total;
    }
    @Override
    public ArrayList<Product> GetAllProducts() {
        ResponseEntity<String> responseData = RequesterGetAllProduct();
        JSONArray documentsArray = GetDocumentArray(responseData);
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < documentsArray.length(); i++) {
            JSONObject document = documentsArray.getJSONObject(i);
            String name = document.getString("Name");
            double price = document.getDouble("Price");
            String location = document.getString("Location");
            int numberInStore = document.getInt("InventoryCount");
            products.add(new Product(name, price, location, numberInStore));
        }
        return products;
    }

    @Override
    public ArrayList<Product> GetProducts(String ProductName) {
        ResponseEntity<String> responseData = getRequesters(ProductName);
        JSONArray documentsArray = GetDocumentArray(responseData);
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < documentsArray.length(); i++) {
            JSONObject document = documentsArray.getJSONObject(i);
            String name = document.getString("Name");
            double price = document.getDouble("Price");
            String location = document.getString("Location");
            int numberInStore = document.getInt("InventoryCount");
            products.add(new Product(name, price, location, numberInStore));
        }
        return products;
    }

    //doesnt really need this method.
    public JSONObject prepareProductJSON(Product product){
        JSONObject productJSON = new JSONObject();
        try {
            productJSON.put("Name", product.getName());
            productJSON.put("Price", product.getPrice());
            productJSON.put("Location", product.getLocation());
            productJSON.put("InventoryCount", product.getNumberInStore());
        } catch (JSONException e) {
            System.out.println("JSON Convertion error");
            e.printStackTrace();
        }
        System.out.println("Product JSON: " + productJSON.toString());
        return productJSON;
    }
    public ResponseEntity Sender(Product product) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("Name", product.getName());
        filter.put("Price", String.valueOf(product.getPrice()));
        filter.put("Location", product.getLocation());
        filter.put("InventoryCount", String.valueOf(product.getNumberInStore()));
        map.put("collection", "Products");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("document", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.INSERTONE(), entity, String.class);
        System.out.println(response);
        return response;
    }








    public ResponseEntity getRequesters(String ProductName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("Name", ProductName);
        map.put("collection", "Products");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("filter", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.FIND(), entity, String.class);
        System.out.println(response);
        return response;
    }

    public ResponseEntity RequesterGetAllProduct() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
       // filter.put("Name", ProductName);
        map.put("collection", "Products");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("filter", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.FIND(), entity, String.class);
        System.out.println(response);
        return response;
    }
    public ResponseEntity DeleteProduct(String Name){
        System.out.println();
        JSONArray documentArray = dbHelper.GetDocumentArray(backEndProductsProvider.getRequesters(Name));
        for(int i = 0; i < documentArray.length(); i++){
            JSONObject document = documentArray.getJSONObject(i);

            String id = document.getString("Name");
            System.out.println("id: "+id);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Access-Control-Request-Headers", "*");
            headers.set("api-key", Key.APIKEY());
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> filter = new HashMap<>();
            filter.put("Name", Name);
            map.put("collection", "Products");
            map.put("database", "CompSciFinal");
            map.put("dataSource", "ItemList");
            map.put("filter", filter);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(Key.DELETEONE(), entity, String.class);
            System.out.println(response);
        }
        return null;
    }


}