package com.brighttalk.employee.expection;

public class EmployeeNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
        super("Book id not found : " + id);
    }

}