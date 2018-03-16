package com.example.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.dao.CustomerRepository;
import com.example.customer.models.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value="Customer API", description="Operations pertaining to Customer API")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	protected static Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());
	
	
	@ApiOperation(value = "View Customer Details by Customer Number",response = Customer.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved Customer"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	)
   @RequestMapping(value="/customer/{customerNumber}",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<Customer> findByCustomerNumber(@PathVariable Integer customerNumber) {
		
		logger.info(String.format("Customer.findByCustomerNumber(%s)", customerNumber));
		
		Customer customer = customerRepository.findOne(customerNumber.longValue());;
		
		if(customer == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(customer);
	
	}
	
	@ApiOperation(value = "View All Customer",response = Iterable.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully Retrieved Customer List"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	)
   @RequestMapping(value="/customer",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<Iterable<Customer>> findAllCustomers() {
		
		logger.info(String.format("Customer.findAllCustomer"));
		
		Iterable<Customer> accounts = customerRepository.findAll();
		
		return ResponseEntity.ok().body(accounts);
	}
	
}
