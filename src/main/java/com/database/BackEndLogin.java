package com.database;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        headers.set("api-key", "UloVIfogqJQmpAVKvk1vp6Cg6gXLLNZDnCURuoxAtU4D5GuAitTQZx3ccPR5SxJE");
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

        ResponseEntity<String> response = restTemplate.postForEntity("https://us-west-2.aws.data.mongodb-api.com/app/data-lfiqp/endpoint/data/v1/action/find", entity, String.class);
        System.out.println(response);
        return response;
    }

}
/*
curl --location --request POST 'https://us-west-2.aws.data.mongodb-api.com/app/data-lfiqp/endpoint/data/v1/action/findOne' \
--header 'Content-Type: application/json' \
--header 'Access-Control-Request-Headers: *' \
--header 'api-key: UloVIfogqJQmpAVKvk1vp6Cg6gXLLNZDnCURuoxAtU4D5GuAitTQZx3ccPR5SxJE' \
--data-raw '{
    "collection":"LoginInfo",
    "database":"CompSciFinal",
    "dataSource":"ItemList",
    "projection": {"_id": 1}
}'

 */
