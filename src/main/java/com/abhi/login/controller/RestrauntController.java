package com.abhi.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.login.model.CustomerDTO;

@RestController
public class RestrauntController {

	@GetMapping("/customer")
	public List<CustomerDTO> getdashboard() {
		
		List<CustomerDTO> availableCust = new ArrayList<>();
		availableCust.add(new CustomerDTO(1, "Abhishek"));
		availableCust.add(new CustomerDTO(2, "Kiran"));
		return availableCust;
	}
}
