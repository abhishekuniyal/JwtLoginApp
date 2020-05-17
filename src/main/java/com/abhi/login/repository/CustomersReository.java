package com.abhi.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.login.entity.Customer;

@Repository
public interface CustomersReository extends JpaRepository<Customer, Integer>{

}
