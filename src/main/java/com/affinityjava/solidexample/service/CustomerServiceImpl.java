package com.affinityjava.solidexample.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.affinityjava.solidexample.model.Customer;
import com.affinityjava.solidexample.repository.CustomerRepository;
import com.opencsv.CSVWriter;

@Component("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public void setCustomerRepository(CustomerRepository repository) {
		this.customerRepository = repository;
	}

	@Override
	public void readCustomersAndWriteToCSV() {

		Iterable<Customer> customers = customerRepository.findAll();

		String filePath = "C:\\Customer.csv";
		File file = new File(filePath);
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile);
			String[] header = { "id", "first name", "last name", "address" };
			writer.writeNext(header);
			for (Customer customer : customers) {
				String[] data = new String[4];
				data[0] = String.valueOf(customer.getId());
				data[1] = customer.getCustomerFirstName();
				data[2] = customer.getCustomerLastName();
				data[3] = customer.getCustomerAddress();
				writer.writeNext(data);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
