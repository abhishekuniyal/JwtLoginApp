package com.abhi.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.login.entity.Items;

@Repository
public interface ItemsReository extends JpaRepository<Items, Integer>{

	
}
