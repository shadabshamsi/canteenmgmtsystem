package com.canteenmanagement.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canteenmanagement.pojos.Customer;
import com.canteenmanagement.pojos.Order;
import com.canteenmanagement.services.CustomerService;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public List<Customer> getCustomers(){
		return customerService.get();
	}

	@GetMapping("/{custId}")
	public Customer getCustomer(@PathVariable Integer custId) {
		return customerService.get(custId);
	}
	
	@PostMapping
	public Integer addCustomer(@RequestBody Customer customer) {
		if(customer != null)
			return customerService.add(customer);
		return -1;
	}
	
	@PutMapping("/")
	public Customer updateCustomer(@RequestBody Customer customer) {
		if(customer != null)
			return customerService.update(customer);
		return null;
	}
	
//	@PutMapping("/UpdatePassword/{id}")
//	public Customer updatePassword(@PathVariable Integer id, @RequestBody String oldPassword, @RequestBody String newPassword) {
//		System.out.println("Customer Id: "+id);
//		System.out.println("Old Password: "+oldPassword);
//		System.out.println("New Password: "+newPassword);
//		Customer customer = customerService.get(id);
//		return customer;
//	}
	
	@DeleteMapping("/{custId}")
	public Customer deleteCustomer(@PathVariable Integer custId) {
		return customerService.delete(custId);
	}
	
	@PostMapping("/IsUniqueEmail")
	public boolean isUniqueEmail(@RequestBody String email){
		return customerService.isUniqueEmail(email);
	}
	
	@PostMapping("/IsUniquePhoneNo")
	public boolean isUniquePhoneNo(@RequestBody String phoneNo){
		return customerService.isUniquePhoneNo(phoneNo);
	}
	
	@GetMapping("{custId}/Orders")
	public List<Order> getOrders(@PathVariable Integer custId){
		System.out.println(custId);
		return customerService.getOrders(custId);
	}
}
