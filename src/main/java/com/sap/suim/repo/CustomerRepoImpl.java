package com.sap.suim.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.suim.entities.Customer;

@Service
public class CustomerRepoImpl {
	
	@Autowired
	public CustomerRepo repo;
	
	/*
	 * @Autowired public CustomerRepoImpl(CustomerRepo repo) { this.repo = repo;}
	 */
	
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}
	
	public Customer storeCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public List<Customer> storeMultipleCustomers(List<Customer> customers) {
		return repo.saveAll(customers);
	}
	public Customer getCustomerById(Long id) {
		return repo.findById(id).orElse(null);
	}
	public List<Customer> getCustomerByFirstName(String name) {
		return repo.findCustomerByFirstName(name);
	}
	public void deleteCustomer(Customer customer) {
		repo.delete(customer);
	}
	public void deleteCustomerById(Long id) {
		repo.deleteById(id);
	}
	public void deleteCustomerByName(String name) {
		repo.deleteByName(name);
	}
}
