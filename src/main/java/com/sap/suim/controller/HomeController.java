package com.sap.suim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sap.suim.entities.Customer;
import com.sap.suim.repo.CustomerRepoImpl;

@RestController
public class HomeController {
	
	@Autowired
	public CustomerRepoImpl repo;
	
	/*
	 * @Autowired public HomeController(CustomerRepoImpl repo) { this.repo=repo; }
	 */
	
	@GetMapping("hello")
	public String testController() {
		return "Hello All";
	}
   @GetMapping("getCustomers")
    public List<Customer> getAllCustomers() {
        return repo.getAllCustomers();
    }
   @PostMapping("saveCustomer")
   public Customer addCustomer(@RequestBody Customer customer) {
	   return repo.storeCustomer(customer);
   }
   @PostMapping("saveMultipleCustomers")
   public List<Customer> addMultipleCustomers(@RequestBody List<Customer> customers) {
	   return repo.storeMultipleCustomers(customers);
   }
  
   @GetMapping("getCustomerById/{id}")
   public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
	   Customer customer = repo.getCustomerById(id);
	   if(customer!=null) 
		   return ResponseEntity.ok(customer);
	   else
		   return ResponseEntity.notFound().build();
   }
   
   @GetMapping("getCustomerByName/{name}")
   public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String name) {
	   List<Customer> c = repo.getCustomerByFirstName(name);
	   if(!c.isEmpty()) {
		   return ResponseEntity.status(HttpStatus.FOUND).body(c);
	   }
	   else
		   return ResponseEntity.notFound().build();		   
   }
   
   @DeleteMapping("deleteCustomerById/{id}")
   public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {
	   Customer customer = repo.getCustomerById(id);
	   if(customer!=null) {
		   repo.deleteCustomerById(id);
		   return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
	   }
	   else
		   return ResponseEntity.notFound().build();
   }
   
   @DeleteMapping("deleteCustomerByName/{name}")
   public ResponseEntity<List<Customer>> deleteCustomerByName(@PathVariable String name) {
	   List<Customer> c = repo.getCustomerByFirstName(name);
	   if(!c.isEmpty()) {
			repo.deleteCustomerByName(name);
		   return ResponseEntity.status(HttpStatus.CREATED).body(c);
	   }
	   else
		   return null;		   
   }
}
