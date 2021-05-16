package com.brighttalk.employee.expection;

import java.util.Set;

public class EmployeeUnSupportedFieldPatchException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}