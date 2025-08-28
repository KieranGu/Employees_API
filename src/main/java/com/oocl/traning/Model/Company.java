package com.oocl.traning.Model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    private int id;
    private String name;
    List<Employee> employees = new ArrayList<>();

    public Company() {
    }
    public Company(int id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
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
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public String getCompanyIDAndName(){
        return ("Company ID: "+this.id+" Company Name: "+this.name);
    }
}
