package com.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PackageController {

	@GetMapping("/package/addPackage")
	public String addPackage() {
		return "admin/package/package";
	}
	
}
