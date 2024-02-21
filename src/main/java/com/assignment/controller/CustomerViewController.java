package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.assignment.DAO.OrderDAO;
import com.assignment.DAO.PackageDAO;
import com.assignment.DAO.UserDAO;
import com.assignment.entity.Order;

@Controller
public class CustomerViewController {
	@Autowired
	UserDAO userdao;
	@Autowired
	OrderDAO orderdao;
	PackageDAO packagedao;
	@GetMapping("/customer/view")
	public String CustomerViewOrder(Model model) {
		List<Order> list = userdao.findByUsername("admin").getListOrder1();
		model.addAttribute("listOrder",list);
		return "customer/customerView";
	}
	
	@GetMapping("/customer/{x}/{y}")
	public String CustomerViewOrderDetail(Model model,@PathVariable("x") long tripid,
			@PathVariable("y") long oderid) {
		model.addAttribute("listPackage", packagedao.findByPackageId(oderid));
		return "customer/customerViewDetail";
	}
	
}
