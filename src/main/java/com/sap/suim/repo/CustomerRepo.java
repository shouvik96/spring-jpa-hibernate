package com.sap.suim.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sap.suim.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	@Transactional
	@Modifying
	@Query("Delete from Customer c where c.firstName = :name")
	void deleteByName(String name);
	

    @Query("SELECT c FROM Customer c WHERE c.firstName=:name")
    List<Customer> findCustomerByFirstName(String name);

}
