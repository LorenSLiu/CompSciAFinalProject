package com.database;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Key.Key;

public class BackEndLogin {
    //enter for test only
//    public static void main(String[] args) {
//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        System.out.println(databaseConnection.checkResponseData("loren","loren",databaseConnection.Login("loren","loren")));
//    }
    public String getAccessLevel(ResponseEntity<String> ResponseData){
        String body = ResponseData.getBody();
        if(body.contains("Admin")){
            return "Admin";
        } else if (body.contains("Driver") ){
            return "Driver";
        }
        return "Default";
    }

    public boolean checkResponseData(String Username, String Password, ResponseEntity<String> ResponseData) {
        String body = ResponseData.getBody();
        return body != null && body.contains(Username) && body.contains(Password);
    }

    public ResponseEntity Login(String Username, String Password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("Username", Username);
        filter.put("Password", Password);
        map.put("collection", "LoginInfo");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("filter", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.FIND(), entity, String.class);
        System.out.println(response);
        return response;
    }

}
