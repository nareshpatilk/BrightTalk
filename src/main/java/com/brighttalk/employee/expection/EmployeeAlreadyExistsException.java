package com.brighttalk.employee.expection;

public class EmployeeAlreadyExistsException  extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyExistsException() {
		super("Employee Already Exists!!");
	}

}
