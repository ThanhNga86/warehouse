package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.User;

@Controller
public class dangkyController {
	@Autowired
	UserDAO userdao;
	
	@RequestMapping("/register")
	public String dangky() {
		return "admin/layout/register";
	}
	
	@RequestMapping("/create")
	public String create(Model model,User dkitem,BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("message", "một số testfield chưa dc xử lí!!");
		}else {
			userdao.save(dkitem);
			model.addAttribute("message", "đăng ký thành công!!");
		}
		

		return "redirect:admin/layout/register";
	}
	
}
