package com.assignment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.User;


@Controller
public class UserCRUDController {

	@Autowired
	UserDAO userdao;
	@GetMapping("staff")
	public String userView(Model model) {
		model.addAttribute("users", userdao.findByActive(true));
		return "users/userCRUD";
	}
	
	@GetMapping("user/userdelete")
	public String userEdit(Model model,@RequestParam String userId) {
		Optional<User> user = userdao.findById(userId);
		user.get().setActive(false);
		userdao.save(user.get());
		return "redirect:/staff";
	}
	
}
