package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.dao.customerDao;
import com.test.model.*;
import java.util.*;
@RestController
public class CustomerRestController {
	@Autowired
	customerDao customerdao;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerdao.list();
		
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable("id") Long id) {

		Customer customer = customerdao.get(id);
		if (customer == null) {
			return new ResponseEntity<String>("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/customers")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

		customerdao.create(customer);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

		if (null == customerdao.delete(id)) {
			return new ResponseEntity<String>("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		customer = customerdao.update(id, customer);

		if (null == customer) {
			return new ResponseEntity<String>("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

}
