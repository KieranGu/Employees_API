package com.oocl.traning.Controller;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String gender;
    private int age;
    private int salary;

    public Employee() {
    }
    public Employee(int employeeID,String employeeName,String gender){
        this.employeeID=employeeID;
        this.employeeName=employeeName;
        this.gender=gender;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
