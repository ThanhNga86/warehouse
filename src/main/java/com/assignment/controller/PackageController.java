package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.assignment.entity.WareHouse;
import com.assignment.repo.WareHouseRepository;

@Controller
public class PackageController {
	@Autowired
	WareHouseRepository Warehousedao;

	@GetMapping("/package/addPackage")
	public String addPackage(Model model) {
		WareHouse WHitem = new WareHouse();
		model.addAttribute("WHitem", WHitem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
		return "/admin/package/package";
	}
	
}
