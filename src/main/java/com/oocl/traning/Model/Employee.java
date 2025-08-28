package com.oocl.traning.Model;

import jakarta.persistence.*;

@Entity//与某张表产生映射关系
@Table(name="employee")//指定映射的表名
public class Employee {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer employeeID;
    private String employeeName;
    private String gender;
    private int age;
    private double salary;
    private Boolean isActive =true;

    public Employee() {
    }
    public Employee(Integer employeeID,String employeeName,int age,String gender,double salary){
        this.employeeID=employeeID;
        this.employeeName=employeeName;
        this.gender=gender;
        this.age=age;
        this.salary=salary;
    }
    public Employee(String employeeName,int age,String gender,double salary){
        this.employeeID=null;
        this.employeeName=employeeName;
        this.gender=gender;
        this.age=age;
        this.salary=salary;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(Integer employeeID) {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
