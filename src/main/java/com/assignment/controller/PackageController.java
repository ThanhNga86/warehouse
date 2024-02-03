package com.assignment.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PackageController {

	@GetMapping("/package/addPackage")
<<<<<<< HEAD
	public String addPackage(Model model) {
		WareHouse WHitem = new WareHouse();
		model.addAttribute("WHitem", WHitem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
		return "/admin/package/package";
=======
	public String addPackage() {
		return "admin/package/package";
>>>>>>> b2a46965f2cd2b1e80d029987062bf7d16a3a343
	}
}
