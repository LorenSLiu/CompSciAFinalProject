package com.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

public class DBHelper {
    public static JSONObject ResponseEntityToJSON(ResponseEntity<String> ResponseData) {
        JSONObject jsonObject = new JSONObject(ResponseData.getBody());
        try {
            JSONArray documentsArray = jsonObject.getJSONArray("documents");
        } catch (JSONException e) {
            System.out.println("No documents found");
        }
        return jsonObject;
    }

    public static JSONArray GetDocumentArray(ResponseEntity<String> ResponseData) {
        JSONObject jsonObject = new JSONObject(ResponseData.getBody());
        JSONArray documentsArray = jsonObject.getJSONArray("documents");
        return documentsArray;
    }
}
