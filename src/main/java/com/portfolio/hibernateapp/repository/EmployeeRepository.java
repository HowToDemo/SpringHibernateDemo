package com.portfolio.hibernateapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.hibernateapp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
