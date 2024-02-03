package com.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

	@GetMapping("/package/createOrder")
	public String createOrder() {
		return "admin/order/order";
	}
}
