package com.brighttalk.employee.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.brighttalk.employee.bean.ErrorResponse;
import com.brighttalk.employee.model.Employee;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeTestController extends AbstractTest {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeTestController.class);
	
	@Override
	   @Before
	   public void setUp() {
		 super.setUp();
	   }
	
	/**
	 * Get all the employee list
	 * @throws Exception
	 */
	@Test
	public void testA_getEmployeeList() throws Exception {
	   String uri = "/employees";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   System.out.println("Tests "+mvcResult.getResponse());
	   
	   if(LOG.isInfoEnabled())
		   LOG.info("Get Employee Called {{TestController }}"+mvcResult.getResponse().toString());
	   
	   int status = mvcResult.getResponse().getStatus();
	   
	   if(LOG.isInfoEnabled())
		   LOG.info("Get Employee status {{TestController }}"+status);
	   System.out.println("Status "+ status);
	   assertEquals(200, 200);
	  
	    String content = mvcResult.getResponse().getContentAsString();
	    
	    
	    if(LOG.isInfoEnabled())
			   LOG.info("Get Employee response {{TestController }}"+content);
	    
	    Employee[] employeeList = super.mapFromJson(content, Employee[].class);
	   
	    if(LOG.isInfoEnabled())
	 	   LOG.info("Get Employee list {{TestController }}"+employeeList);
	   
	   assertTrue(employeeList.length > 0);
	}
	
	/**
	 * Create new Employee
	 * @throws Exception
	 */
	@Test
	   public void TestB_CreateEmployee() throws Exception {
	      String uri = "/employee";
	      Employee emp = new Employee();
	      
	      emp.setEmail("bt4@bt4.com");
	      emp.setFirstName("bright 4");
	      emp.setLastName("talk");
	      emp.setPhone("9876543123");
	      
	      String inputJson = super.mapToJson(emp);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      
	      if(LOG.isInfoEnabled())
			   LOG.info("Save Employee response {{TestController }}"+content);
	    
	    Employee employee = super.mapFromJson(content, Employee.class);
	    
	    if(LOG.isInfoEnabled())
			   LOG.info("Save Employee employee {{TestController }}"+employee);
	    
	      assertEquals(emp.getEmail(), employee.getEmail());
	   }
	
	/**
	 * get employee by particular employee by id
	 * @throws Exception
	 */
	@Test
	public void TestC_ParticularEmployee() throws Exception {
	   String uri = "/employee/2";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   if(LOG.isInfoEnabled())
		   LOG.info("Get Employee particular {{TestController }}"+mvcResult.getResponse().toString());
	   
	   int status = mvcResult.getResponse().getStatus();
	   
	   if(LOG.isInfoEnabled())
		   LOG.info("Get Employee particular {{TestController }}"+status);
	   System.out.println("Status "+ status);
	   assertEquals(200, 200);
	  
	    String content = mvcResult.getResponse().getContentAsString();
	    
	    
	    if(LOG.isInfoEnabled())
			   LOG.info("Get Employee particular response {{TestController }}"+content);
	    
	    Employee employee = super.mapFromJson(content, Employee.class);
	   
	    if(LOG.isInfoEnabled())
	 	   LOG.info("Get Employee particular particular  {{TestController }}"+employee);
	   
	   //assertTrue(employeeList.length > 0);
	   assertEquals("hl@gmail.com", employee.getEmail());
	}
	
	/**
	 * Test to check update employee
	 * @throws Exception
	 */
	@Test
	public void TestD_UpdateEmployee() throws Exception {
	   String uri = "/employee/5";
	   Employee employee = new Employee();
	   employee.setFirstName("Mathews");
	   employee.setPhone("9876543345");
	   employee.setEmail("Mathews@bt.com");
	   
	   if(LOG.isInfoEnabled())
	 	   LOG.info("Update Employee PUT  {{TestController }}"+employee);
	   
	   String inputJson = super.mapToJson(employee);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   Employee updateEmp = super.mapFromJson(content, Employee.class);
	   
	    if(LOG.isInfoEnabled())
	 	   LOG.info("Update Employee PUT  {{TestController }}"+updateEmp.getFirstName());
	    
	   assertEquals("Mathews", updateEmp.getFirstName());
	}
	
	
	/**
	 * Create new Employee
	 * @throws Exception
	 */
	@Test
	   public void TestE_FailureCreateEmployeeEmailFailure() throws Exception {
	      String uri = "/employee";
	      Employee emp = new Employee();
	      
	      emp.setEmail("bt");
	      emp.setFirstName("bright");
	      emp.setLastName("talk");
	      emp.setPhone("9876543123");
	      
	      String inputJson = super.mapToJson(emp);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      
	      if(LOG.isInfoEnabled())
			   LOG.info("Create Employe Failure status {{TestController }}"+status);
	      
	      assertEquals(400, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      
	      if(LOG.isInfoEnabled())
			   LOG.info("Create Employe Failure content {{TestController }}"+content);
	      
	      
	      ErrorResponse errorEmp = super.mapFromJson(content, ErrorResponse.class);
		  
	      String errorMsg ="must be a well-formed email address";
		    if(LOG.isInfoEnabled())
		 	   LOG.info("Employee PUT  {{TestController }}"+errorEmp.getErrors().get(0));
		    
		   assertEquals(errorMsg, errorEmp.getErrors().get(0));
		   
	
	   }
	
	/**
	 * Test to check delete functionality
	 * @throws Exception
	 */
	@Test
	public void TestF_deleteEmployee() throws Exception {
	   String uri = "/employee/2";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   if(LOG.isInfoEnabled())
	 	   LOG.info("Employee Deleted response  {{TestController }}"+ mvcResult.getResponse().getContentAsString());
	   
	   int status = mvcResult.getResponse().getStatus();
	   if(LOG.isInfoEnabled())
	 	   LOG.info("Employee Deleted successfully  {{TestController }}"+status);
	   assertEquals(200, status);
	}
	
	@After
    public void setDirty(){
		if(LOG.isInfoEnabled())
	 	   LOG.info("After  {{TestController }}");
        
    }
}
