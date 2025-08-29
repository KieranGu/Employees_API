package com.oocl.traning.controller.dto;

public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Integer id,String name,Integer age,String gender){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
    }

}
