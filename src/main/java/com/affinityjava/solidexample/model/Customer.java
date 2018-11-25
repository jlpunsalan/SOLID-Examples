package com.affinityjava.solidexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Customer {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 
	 @Column(nullable = false)
	 private String customerLastName;
	 
	 @Column(nullable = false)
	 private String customerFirstName;
	 
	 @Column(nullable = false)
	 private String customerAddress;
	
}
