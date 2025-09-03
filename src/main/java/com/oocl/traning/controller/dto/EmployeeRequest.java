package com.oocl.traning.controller.dto;

import com.oocl.traning.model.Company;
import com.oocl.traning.model.Employee;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequest {
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value=18, message="Age should not be less than 18")
    @Max(value=65, message="Age should not be greater than 65")
    private Integer age;
    private String gender;
    private Integer companyId;

    public String getCompanyId() {
        return companyId.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
