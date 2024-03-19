package com.database;

//import com.application.description.Admin;
import Key.Key;
import com.application.description.Employee;
import com.application.description.EmployeeBehavior;
import com.application.description.Employees;
import org.json.JSONArray;
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

import static com.database.DBHelper.GetDocumentArray;
@SuppressWarnings("all")
public class BackEndUserManager implements EmployeeBehavior {
    static DBHelper dbHelper = new DBHelper();
    public static void main(String[] args) {
        BackEndUserManager BackEndUserManager = new BackEndUserManager();
        BackEndUserManager.DeleteUser("test","test");
//        System.out.println();
//        System.out.println(BackEndUserManager.Requester("John"));
//        System.out.println(BackEndUserManager.Requester("John", "Doe"));
//        System.out.println(BackEndUserManager.Requester("Loren"));
    }
    @Override
    public ArrayList<Employee> GetUser(String firstName) {
        ResponseEntity<String> responseData = Requester(firstName);
        JSONArray documentsArray = GetDocumentArray(responseData);
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < documentsArray.length(); i++) {
            JSONObject document = documentsArray.getJSONObject(i);
            String FirstName = document.getString("firstName");
            String LastName = document.getString("lastName");
            int salary = document.getInt("salary");
            int annualDayOff = document.getInt("annualLeaveDays");
            int leftDays = document.getInt("leftDays");

            //employees.add(new Employee(FirstName, LastName, salary, annualDayOff, leftDays));
        }
        return employees;
    }
    @Override
    public ArrayList<Employee> GetUser(String firstName, String lastName) {
        ResponseEntity<String> responseData = Requester(firstName, lastName);
        JSONArray documentsArray = GetDocumentArray(responseData);
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < documentsArray.length(); i++) {
            JSONObject document = documentsArray.getJSONObject(i);
            String FirstName = document.getString("firstName");
            String LastName = document.getString("lastName");
            int salary = document.getInt("salary");
            // employees.add(new Admin(FirstName, LastName, salary, 1, "Admin", true));
        }
        return employees;
    }
    @Override
    public ArrayList<Employees> GetAllEmployee() {
        ResponseEntity<String> responseData = RequesterGetAllEmployee();
        JSONArray documentsArray = GetDocumentArray(responseData);
        ArrayList<Employees> employees = new ArrayList<>();
        for (int i = 0; i < documentsArray.length(); i++) {
            JSONObject document = documentsArray.getJSONObject(i);
            String FirstName = document.getString("firstName");
            String LastName = document.getString("lastName");
            String position = document.getString("position");
            double salary = document.getDouble("salary");
            int annualDayOff = document.getInt("annualLeaveDays");
            int leftDays = document.getInt("leftDays");
            double hours = document.getDouble("workingHours");
            boolean finishedTraining = document.getBoolean("yearlyTrainingState");
            employees.add(new Employees(FirstName, LastName, salary, annualDayOff, leftDays, position, hours, finishedTraining));
        }
        return employees;
    }

    public ResponseEntity Sender(Employees employees) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("firstName", employees.getFirstName());
        filter.put("lastName", employees.getLastName());
        filter.put("leftDays", employees.getLeftDays());
        filter.put("position", employees.getPosition());
        filter.put("salary", employees.getSalaries());
        filter.put("workingHours", employees.getHours());
        filter.put("yearlyTrainingState", employees.isFinishedTraining());
        filter.put("annualLeaveDays", employees.getAnnualDayOff());


        map.put("collection", "Employees");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("document", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.INSERTONE(), entity, String.class);
        System.out.println(response);
        return response;
    }




    public ResponseEntity Requester(String firstName, String lastName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("firstName", firstName);
        filter.put("lastName", lastName);
        map.put("collection", "Employees");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("filter", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.FIND(), entity, String.class);
        System.out.println(response);
        return response;
    }

    public ResponseEntity Requester(String firstName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        filter.put("firstName", firstName);
        map.put("collection", "Employees");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("filter", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.FIND(), entity, String.class);
        System.out.println(response);
        return response;
    }

    public ResponseEntity RequesterGetAllEmployee() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Request-Headers", "*");
        headers.set("api-key", Key.APIKEY());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> filter = new HashMap<>();
        map.put("collection", "Employees");
        map.put("database", "CompSciFinal");
        map.put("dataSource", "ItemList");
        map.put("filter", filter);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(Key.FIND(), entity, String.class);
        System.out.println(response);
        return response;
    }
    public ResponseEntity DeleteUser(String FistName, String LastName){
        System.out.println();
        JSONArray documentArray = dbHelper.GetDocumentArray(Requester(FistName, LastName));
        for(int i = 0; i < documentArray.length(); i++){
            JSONObject document = documentArray.getJSONObject(i);

            String id = document.getString("firstName");

            System.out.println("id: "+id);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Access-Control-Request-Headers", "*");
            headers.set("api-key", Key.APIKEY());
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> filter = new HashMap<>();
            filter.put("firstName", id);
            map.put("collection", "Employees");
            map.put("database", "CompSciFinal");
            map.put("dataSource", "ItemList");
            map.put("filter", filter);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(Key.DELETEONE(), entity, String.class);
            System.out.println("你冒头"+response);
        }
        return null;
    }



}
