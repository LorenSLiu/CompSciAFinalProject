package com.application.description;

public class Admin extends Employees implements EmployeeBehavior{
    @Override
    public boolean requestDayOff(String requestID) {
        return false;
    }




}
