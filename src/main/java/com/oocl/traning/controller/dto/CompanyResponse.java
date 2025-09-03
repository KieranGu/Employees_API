package com.oocl.traning.controller.dto;

public class CompanyResponse {
    private Integer id;
    private String name;

    public CompanyResponse() {
    }

    public CompanyResponse(Integer id, String name, Integer employeeNumber, String address) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
