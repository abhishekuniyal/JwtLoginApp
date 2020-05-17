package com.abhi.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.login.model.CustomerDTO;
import com.abhi.login.model.ItemDTO;
import com.abhi.login.service.RestrauntService;

@RestController
public class RestrauntController {

	@Autowired
	private RestrauntService restrauntService;

	@GetMapping("/customer")
	public List<CustomerDTO> getCustomers(){
				
		return restrauntService.getCustomers();
		
	}

	@GetMapping("/items")
	public List<ItemDTO> getAvailableItems() {
		return restrauntService.getAvailableItems();
	}
}
