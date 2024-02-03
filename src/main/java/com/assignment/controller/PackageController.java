package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.assignment.DAO.PackageDAO;
import com.assignment.entity.Package;
import com.assignment.entity.WareHouse;

@Controller
public class PackageController {
	@Autowired
	private PackageDAO packageDAO;

	@GetMapping("/package/addPackage")
	public String addPackage(Model model) {
		WareHouse WHitem = new WareHouse();
		model.addAttribute("WHitem", WHitem);
		List<Package> WHitems = packageDAO.findAll();
		model.addAttribute("WHitems", WHitems);
		return "/admin/package/package";
	}

	public String addPackage() {
		return "admin/package/package";
	}
}
