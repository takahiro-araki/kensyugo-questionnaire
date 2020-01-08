package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	List<Employee>findByNameEquals(String name);
	
	

}
