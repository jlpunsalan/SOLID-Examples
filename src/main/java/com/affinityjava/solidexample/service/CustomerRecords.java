package com.affinityjava.solidexample.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.affinityjava.solidexample.model.Customer;
import com.opencsv.CSVWriter;

public class CustomerRecords implements ICustomerRecords {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/H2DB";
	static final String USER = "username";
	static final String PASS = "password";

	@Override
	public List<Customer> findAll() {
		Connection conn = null;
		Statement stmt = null;
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, customerLastName, customerFirstName, customerAddress FROM CUSTOMERS";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setCustomerFirstName(rs.getString("customerFirstName"));
				customer.setCustomerLastName(rs.getString("customerLastName"));
				customer.setCustomerAddress(rs.getString("customerAddress"));
				customerList.add(customer);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return customerList;
	}

	@Override
	public void findCustomersAndWriteToCSV() {

		String filePath = "C:\\Customer.csv";
		File file = new File(filePath);
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile);
			String[] header = { "id", "first name", "last name", "address" };
			writer.writeNext(header);
			for(Customer customer: this.findAll()) {
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
