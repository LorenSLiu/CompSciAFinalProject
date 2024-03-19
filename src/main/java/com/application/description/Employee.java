package com.application.description;


public abstract class Employee {
    private String firstName;
    private String lastName;
    private double Salaries;
    private int annualDayOff;
    private int leftDays;
    private String position;
    private double hours;
    private boolean finishedTraining;

    public int getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(int leftDays) {
        this.leftDays = leftDays;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalaries() {
        return Salaries;
    }

    public void setSalaries(double salaries) {
        Salaries = salaries;
    }

    public int getAnnualDayOff() {
        return annualDayOff;
    }

    public void setAnnualDayOff(int annualDayOff) {
        this.annualDayOff = annualDayOff;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isFinishedTraining() {
        return finishedTraining;
    }

    public void setFinishedTraining(boolean finishedTraining) {
        this.finishedTraining = finishedTraining;
    }

    public Employee(String firstName, String lastName, double salaries, int annualDayOff, int leftDays, String position, double hours, boolean finishedTraining) {
        this.firstName = firstName;
        this.lastName = lastName;
        Salaries = salaries;
        this.annualDayOff = annualDayOff;
        this.leftDays = leftDays;
        this.position = position;
        this.hours = hours;
        this.finishedTraining = finishedTraining;
    }
    public Employee(){}

    public Employee(String firstName, String lastName, double salaries, int annualDayOff, int leftDays, String position, boolean finishedTraining) {
        this.firstName = firstName;
        this.lastName = lastName;
        Salaries = salaries;
        this.annualDayOff = annualDayOff;
        this.position = position;
        this.finishedTraining = finishedTraining;
    }

    public Employee(String firstName, String lastName, double salaries, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        Salaries = salaries;
        this.position = position;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
