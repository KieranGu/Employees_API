package com.oocl.traning.repository;

import com.oocl.traning.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface JPAEmployeeRepository extends JpaRepository<Employee,Integer> {
    ArrayList<Employee> getEmployeesByGender(String gender);
}
