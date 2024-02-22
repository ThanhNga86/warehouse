package com.assignment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.Role;
import com.assignment.entity.User;


@Controller
public class UserCRUDController {

	@Autowired
	UserDAO userdao;
	@GetMapping("staff")
	public String userView(Model model) {
		model.addAttribute("users", userdao.findByActiveAndRole(true,Role.ROLE_STAFF));
		model.addAttribute("userstaff", new User());
		return "users/userCRUD";
	}
	
	@GetMapping("user/userdelete")
	public String userDelete(Model model,@RequestParam String userId) {
		Optional<User> user = userdao.findById(userId);
		user.get().setActive(false);
		userdao.save(user.get());
		return "redirect:/staff";
	}
	@GetMapping("user/useredit")
	public String userEdit(Model model, @RequestParam String userId) {
		model.addAttribute("users", userdao.findByActiveAndRole(true,Role.ROLE_STAFF));
		model.addAttribute("userstaff", userdao.findByUsername(userId));
		return "users/userCRUD";	
	}
	@GetMapping("user/update")
	public String userUpdate(Model model,@RequestParam("username") String username
			,@ModelAttribute User userstaff) {
		User user = userdao.findByUsername(username);
		user.setEmail(userstaff.getEmail());
		user.setFullname(userstaff.getFullname());
		user.setPhone(userstaff.getPhone());
		userdao.save(user);
		return "redirect:/staff";
	}
	@GetMapping("user/search")
	public String userSearch(Model model,@RequestParam("search") String value) {
		model.addAttribute("users", userdao.findByActiveAndRoleAndUsernameLike(true, Role.ROLE_STAFF, value));
		model.addAttribute("userstaff", new User());
		return "users/userCRUD";
	}
	
	
}
