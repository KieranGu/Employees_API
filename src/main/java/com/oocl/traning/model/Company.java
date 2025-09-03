package com.oocl.traning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "company_id") // Employee 表里要有这个字段
    @JsonManagedReference
    List<Employee> employees = new ArrayList<>();

    public Company() {
    }
    public Company(int id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;

        for (Employee e : employees) {
            e.setCompany(this); // 设置反向关系
        }

    }
    public Company(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;

        for (Employee e : employees) {
            e.setCompany(this); // 设置反向关系
        }

    }
    public Company(String name) {
        this.name = name;
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
