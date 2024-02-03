package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.assignment.DAO.OrderDAO;
import com.assignment.DAO.PackageDAO;
import com.assignment.DAO.UserDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerViewController {
	@Autowired
	HttpSession session;
	@Autowired
	UserDAO userdao;
	@Autowired
	OrderDAO orderdao;
	@Autowired
	PackageDAO packagedao;
	
	@GetMapping("/customer/view")
	public String customerView(Model model) {
		model.addAttribute("listOrder", orderdao.findByUserCustomer
				(userdao.findByUsername((String) session.getAttribute("username"))));
		return "/customer/customerView";
	}
	
	@GetMapping("/customer/{x}/{y}")
	public String customerViewDetail(Model model, @PathVariable("x")long tripId,
			@PathVariable("y")long orderId) {
		model.addAttribute("listPackage", packagedao.findByOrderId(orderId));
		
		return "/customer/customerViewDetail";
	}
		
		
	
}
