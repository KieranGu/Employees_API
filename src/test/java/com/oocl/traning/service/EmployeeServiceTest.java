package com.oocl.traning.service;

import com.oocl.traning.Model.Employee;
import com.oocl.traning.Repository.EmployeeRepository;
import com.oocl.traning.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Equals;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)//关联spring
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks//注入Service
    private EmployeeService employeeService;
    @Test
    void should_create_employee_successfully(){
        //Given
        Employee employee=new Employee("oocl",20,"MALE",10000);
        Employee mockedEmployee =new Employee(1,employee.getEmployeeName(),employee.getAge(),employee.getGender(),employee.getSalary());
        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenReturn(mockedEmployee);//这段不懂嘞
        //Mockito.when(...):当某个方法被调用时，应该返回什么
        //Mockito.any(Employee.class):无论传入什么 Employee 对象，都匹配成功
        //When
        Employee savedEmployee=employeeService.createEmployee(employee);
        //Then
        assertEquals(employee.getAge(),savedEmployee.getAge());
        assertEquals(employee.getEmployeeName(),savedEmployee.getEmployeeName());
        assertEquals(employee.getGender(),savedEmployee.getGender());
        assertEquals(employee.getSalary(),savedEmployee.getSalary());
        assertEquals(employee.getIsActive(),savedEmployee.getIsActive());
        assertNotNull(savedEmployee.getEmployeeID());
    }

    @Test
    void should_throw_exception_when_create_employee_with_invalid_age(){
        //Given
        Employee employee=new Employee("oocl",16,"MALE",10000);
        //When
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,
                ()->employeeService.createEmployee(employee));
        assertEquals("Age must be between 18 and 65",exception.getMessage());
    }

    @Test
    void should_throw_excepition_when_create_employee_with_unmatched_age_and_salary(){
        //Given
        Employee employee=new Employee("oocl",33,"MALE",16000);
        //When
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,
                ()->employeeService.createEmployee(employee));
        assertEquals("Salary must be at least 20000 for employees over 30 years old",exception.getMessage());
    }

    @Test
    void test_get_employee_by_id()
    {
        //Given
        Employee employee=new Employee("oocl22",20,"MALE",10000);
        Employee mockedEmployee =new Employee(1,employee.getEmployeeName(),employee.getAge(),employee.getGender(),employee.getSalary());
        employeeService.createEmployee(employee);
        when(employeeRepository.findById(Mockito.anyInt()))
                .thenReturn(mockedEmployee);
        //When
        Employee savedEmployee=employeeService.getEmployeeById(1);
        //Then
        assertEquals(employee.getAge(),savedEmployee.getAge());
        assertEquals(employee.getEmployeeName(),savedEmployee.getEmployeeName());
    }

    @Test
    void test_get_all_employees()
    {
        //Given
        Map<Integer, Employee> allEmployees = new HashMap<>(Map.of(
                1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
                2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
                3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
                4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
                5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));
        ArrayList<Employee> employeeArrayList = new ArrayList<>(allEmployees.values());
        when(employeeRepository.findAll())
                .thenReturn(employeeArrayList);
        //When
        List<Employee> savedEmployees=employeeService.getAllEmployees();
//        System.out.println(savedEmployees);
        //Then
        assertEquals(employeeArrayList,savedEmployees);
    }

    @Test
    void should_delete_employee_by_id_successfully(){
        //Given
        Employee employee= new Employee(1,"John Smith",32,"MALE",5000.0);
//        employee.setIsActive(false);

        when(employeeRepository.findById(1)).thenReturn(employee);

        //When
        employeeService.deleteEmployee(1);//由于上面的方法规定把employee返回了，所以一开始定义的employee已经被修改
        //Then
        assertFalse(employee.getIsActive());
        verify(employeeRepository,times(1)).findById(1);
        verify(employeeRepository,times(1)).setAEmployee(1,employee);
    }

}
