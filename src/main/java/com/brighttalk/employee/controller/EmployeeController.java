package com.brighttalk.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brighttalk.employee.expection.EmployeeNotFoundException;
import com.brighttalk.employee.expection.EmployeeUnSupportedFieldPatchException;
import com.brighttalk.employee.model.Employee;
import com.brighttalk.employee.repository.IEmployeeRepository;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class EmployeeController {

    @Autowired
    private IEmployeeRepository repository;

    /**
     * Get list of employee
     * @return
     */
    @GetMapping("/employees")
    List<Employee> findAll() {
        return repository.findAll();
    }

    /**
     * Creates an Employee 
     * @param employee
     * @return
     */
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    Employee newEmployee(@Valid @RequestBody Employee employee) {
    	String email = employee.getEmail();
    	return repository.findByEmail(email)
    			.map(emp -> {
                    emp.setFirstName(employee.getFirstName());
                    emp.setLastName(employee.getLastName());
                    emp.setPhone(employee.getPhone());
                    return repository.save(emp);
                }).orElseGet(() -> {
                    return repository.save(employee);
                });
    			//.orElseThrow(() -> new EmployeeAlreadyExistsException());
        //return repository.save(employee);
    }

    /**
     * Find particular employee
     * @param id
     * @return
     */
    @GetMapping("/employee/{id}")
    Employee findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Update the employee details
     * @param newEmployee
     * @param id
     * @return
     */
    @PutMapping("/employee/{id}")
    Employee saveOrUpdate(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(emp -> {
                    emp.setEmail(newEmployee.getEmail());
                    emp.setFirstName(newEmployee.getFirstName());
                    emp.setLastName(newEmployee.getLastName());
                    emp.setPhone(newEmployee.getPhone());
                    return repository.save(emp);
                })
                .orElseGet(() -> {
                	newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    /**
     * update email only
     * @param update
     * @param id
     * @return
     */
    @SuppressWarnings("deprecation")
	@PatchMapping("/employee/{id}")
    Employee patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String email = update.get("email");
                    if (!StringUtils.isEmpty(email)) {
                        x.setEmail(email);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new EmployeeUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new EmployeeNotFoundException(id);
                });

    }

    /**
     * Delete particular employee
     * @param id
     */
    @DeleteMapping("/employee/{id}")
    void deleteEmplpoyee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
