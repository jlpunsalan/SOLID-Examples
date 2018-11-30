package com.affinityjava.solidexample.service;

import java.util.List;

import com.affinityjava.solidexample.model.Customer;

public interface ICustomerRecords {
	
	List<Customer> findAll();
	
	void findCustomersAndWriteToCSV();

}
