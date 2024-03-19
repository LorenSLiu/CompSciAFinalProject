package com.application.description;

import java.util.ArrayList;

public interface EmployeeBehavior {
    ArrayList<Employee> GetUser(String firstName);
    ArrayList<Employee> GetUser(String firstName, String lastName);
    ArrayList<Employees> GetAllEmployee();

}
