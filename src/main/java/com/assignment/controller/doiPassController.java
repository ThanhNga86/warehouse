package com.assignment.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.MailInfo;
import com.assignment.entity.User;
import com.assignment.service.MailerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class doiPassController {
	@Autowired
	UserDAO userdao;
	@Autowired
	MailerService mailer;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/doimatkhau/{id}")
	public String doipass(Model model,@PathVariable("id") String taikhoan) {
//		MailInfo mail = new MailInfo();
//		model.addAttribute("mail",mail);
		return "redirect:/khachhang/doimatkhau/" + taikhoan;
	}
	
	@RequestMapping("/khachhang/doimatkhau/{id}")
	public String doiPass(Model model, @PathVariable("id") String taikhoan) {
		String matkhau = request.getParameter("password");
		String matkhaumoi = request.getParameter("password1");
		String matkhaulai = request.getParameter("password2");
		try {
			User kh = userdao.findById(taikhoan).get();
		model.addAttribute("item",kh);
		if(!passwordEncoder.matches(matkhau, kh.getPassword())) {
			model.addAttribute("message","Vui lòng đúng nhập mật khẩu");
		}else {
			if(!matkhaumoi.equals(matkhaulai)) {
				model.addAttribute("message","Mật khẩu không trùng khớp");
			}else {
				kh.setPassword(passwordEncoder.encode(matkhaumoi));
				userdao.save(kh);
				model.addAttribute("message","Thành công");
			}
		}
		} catch (Exception e) {
			model.addAttribute("message","Mật khẩu không đúng");
		}
		return "account/login/doipass";
		//account/login/doipass
	}
	
}
