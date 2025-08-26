package com.oocl.traning.Controller;

import java.util.ArrayList;

public class Company {
    private int id;
    private String name;
    ArrayList<Employee> employees = new ArrayList<>();

    public Company() {
    }
    public Company(int id, String name, ArrayList<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public void getCompanyIDAndName(){
        System.out.println("Company ID: "+this.id+" Company Name: "+this.name);
    }
}
