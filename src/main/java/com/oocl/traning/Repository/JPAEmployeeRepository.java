package com.oocl.traning.Repository;

import com.oocl.traning.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface JPAEmployeeRepository extends JpaRepository<Employee,Integer> {
    ArrayList<Employee> getEmployeesByGender(String gender);
}
