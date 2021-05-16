package com.brighttalk.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.brighttalk.employee.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long>,
CrudRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long>,
JpaSpecificationExecutor<Employee>{
	
	/**
	 * Find all the employee
	 */
	List<Employee>  findAll();
	
	/**
	 * Check if employee is present
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("SELECT s FROM Employee s WHERE  s.email=:email AND s.phone=:phone")
	Employee findEmployeeExists(String email, 
			String phone);
	
	/**
	 * Check for duplicate email
	 * @param email
	 * @return
	 */
	Optional<Employee> findByEmail(String email);
}
