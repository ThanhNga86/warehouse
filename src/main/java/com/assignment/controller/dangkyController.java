package com.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.Role;
import com.assignment.entity.User;

@Controller
public class dangkyController {
	@Autowired
	UserDAO userdao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/register")
	public String dangky(Model model) {
		User kh = new User();
		model.addAttribute("kh", kh);
		return "admin/layout/register";
	}
	
	@RequestMapping("/create")
	public String create(Model model,User kh,@RequestParam("passwordlai")String password,BindingResult result) {
		try {
		String regexPhone = "^(0|\\\\+84|84)[0-9]{9}$";
		String regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
		List<String> email = new ArrayList<>();
		List<String> taikhoan = new ArrayList<>();
		List<User> listus = userdao.findAll();
		 for(User user : listus ) {
			 String row = user.getEmail();
			 String rew = user.getUsername();
			 email.add(row);
			 taikhoan.add(rew);
		 }
		if(kh.getUsername().equals("") || kh.getPassword().equals("") || kh.getFullname().equals("") || kh.getEmail().equals("")
				&& kh.getPhone().equals("") || password.equals("")) {
			model.addAttribute("message", "Vui lòng nhập đủ thông tin!!");
		}else {
			if(taikhoan.contains(kh.getUsername())) {
				model.addAttribute("message", "Tài khoản này đã có người đăng ký!!");
			}
			else if(!kh.getPassword().equals(password)){
				model.addAttribute("message", "Mật khẩu không trùng khớp!!");
			}else if(kh.getEmail().matches(regexEmail) == false) {
				model.addAttribute("message", "Sai định dạng Email!!");
			}else if(email.contains(kh.getEmail())) {
			model.addAttribute("message", "Email này đã được có người đăng ký!!");
			}else if(kh.getPhone().matches(regexPhone) == false){
			model.addAttribute("message", "Sai định dạng số điện thoại!!");
			}else {
			kh.setActive(true);
			kh.setRole(Role.ROLE_CUSTOMER);
			kh.setPassword(passwordEncoder.encode(password));
			userdao.save(kh);
			model.addAttribute("message", "đăng ký thành công!!");
		}
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "forward:/register";
	}
	
}
