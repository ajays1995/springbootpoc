package com.example.customer.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.customer.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
