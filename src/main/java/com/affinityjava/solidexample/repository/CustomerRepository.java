package com.affinityjava.solidexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.affinityjava.solidexample.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
