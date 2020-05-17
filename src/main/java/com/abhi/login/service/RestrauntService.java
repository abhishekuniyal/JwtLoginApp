package com.abhi.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.login.entity.Customer;
import com.abhi.login.entity.Items;
import com.abhi.login.model.Car;
import com.abhi.login.model.CustomerDTO;
import com.abhi.login.model.Item;
import com.abhi.login.model.ItemDTO;
import com.abhi.login.model.User;
import com.abhi.login.repository.CustomersReository;
import com.abhi.login.repository.ItemsReository;
import com.abhi.login.serializer.CarSerializer;
import com.abhi.login.serializer.ItemDeserializer;
import com.abhi.login.serializer.ItemSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Service
public class RestrauntService {
	
	@Autowired
	private ItemsReository itemsReository;
	
	@Autowired
	private CustomersReository customersReository;

	public List<ItemDTO> getAvailableItems() {
		
		List<ItemDTO> itemsDto = new ArrayList<>();
		List<Items> items = itemsReository.findAll();
		for(Items item: items ) {
			ItemDTO itemDTO = new ItemDTO();
			BeanUtils.copyProperties(item, itemDTO);
			itemsDto.add(itemDTO);
		}
		
		return itemsDto;		
	}

	public List<CustomerDTO> getCustomers() {
		List<CustomerDTO> customersDto = new ArrayList<>();
		List<Customer> customers = customersReository.findAll();
		for(Customer customer: customers ) {
			CustomerDTO customerDTO = new CustomerDTO();
			BeanUtils.copyProperties(customer, customerDTO);
			customersDto.add(customerDTO);
		}
		
		return customersDto;
	}
	
	public void customeSerializerAndDeserializers() throws JsonMappingException, JsonProcessingException {
		String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonMap = objectMapper.readValue(jsonObject,
		    new TypeReference<Map<String,Object>>(){});
		
		
		String userJson = "{\"id\": 2,\"name\": \"theUser\"}";
		//User user = objectMapper.readValue(userJson, User.class);
		User user = objectMapper.readValue(userJson, new TypeReference<User>() {});

		String json = "{\"id\": 1,\"itemName\": \"theItem\",\"owner\": {\"id\": 2,\"name\": \"theUser\"}}";

		Item item = objectMapper.readValue(json, Item.class);
		System.out.println("item");
		
		String carJson = "{ \"brand\":\"Toyota\", \"doors\":null }";
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		Car c = objectMapper.readValue(carJson, Car.class);
		
		
		/*****Custom Deserializer************/
		
		SimpleModule simpleModule = new SimpleModule("itemDeserializer",new Version(3, 1, 8, null, null, null));
		simpleModule.addDeserializer(Item.class, new ItemDeserializer(Item.class));
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		objectMapper2.registerModule(simpleModule);
		
		String json1 = "{\"itemName\": \"theItem\",\"owner\": {\"userid\": 2,\"name\": \"theUser\"}}";//\"id\": 1,
		Item item1 = objectMapper2.readValue(json1, Item.class);
		//Car c1 = objectMapper2.readValue(carJson, Car.class);
	
		
		/*****Custom Deserializer************/
		
		/************************Custom Serializer*****************************/
		
		ItemSerializer itemSerializer = new ItemSerializer(Item.class);
		ObjectMapper objectMapper4 = new ObjectMapper();

		SimpleModule newmodule =
		        new SimpleModule("ItemSerializer", new Version(2, 1, 3, null, null, null));
		newmodule.addSerializer(Item.class, itemSerializer);

		objectMapper4.registerModule(newmodule);
		String itemJson = objectMapper4.writeValueAsString(item1);
		System.out.println("itemJson::"+itemJson);
		
		
		CarSerializer carSerializer = new CarSerializer(Car.class);
		ObjectMapper objectMapper1 = new ObjectMapper();

		SimpleModule module =
		        new SimpleModule("CarSerializer", new Version(2, 1, 3, null, null, null));
		module.addSerializer(Car.class, carSerializer);

		objectMapper1.registerModule(module);

		Car car = new Car();
		car.setBrand("Mercedes");
		car.setDoors(5);

		String carJson2 = objectMapper.writeValueAsString(car);
		System.out.println("carJson2::"+carJson2);
		
		String carJson22 = objectMapper1.writeValueAsString(car);
		System.out.println("carJson22::"+carJson22);
		/************************Custom Serializer*****************************/
		
		
		//CarDeSerializer carDeSer = new CarDeSerializer(); 

	}
	
	
}
