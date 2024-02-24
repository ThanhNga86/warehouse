package com.assignment.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.MailInfo;
import com.assignment.entity.User;
import com.assignment.service.MailerService;

@Controller
public class quenpassController {
	@Autowired
	UserDAO userdao;
	@Autowired
	MailerService mailer;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/quenmatkhau")
	public String quenpass(Model model) {
//		MailInfo mail = new MailInfo();
//		model.addAttribute("mail",mail);
		return "account/login/quenpass";
	}
	@RequestMapping("/khachhang/quenmatkhau")
	public String quenPass(Model model, @RequestParam("username")String username,@RequestParam("to")String to) {
		Random random = new Random();
		try {
			int randomNumber = random.nextInt(900000) + 100000;
			User kh = userdao.findById(username).get();
			String subject = "Quản lý kho";
			String body = String.valueOf(randomNumber);
			if(username.equals("") || to.equals("")){
				model.addAttribute("message","Vui lòng nhập đủ thông tin !");
			}else {
			if(!kh.getEmail().equals(to)) {
				System.out.println(to);
				System.out.println(kh.getEmail());
				model.addAttribute("message","Tài khoản và email không phù hợp !");
			}else {
				model.addAttribute("message","Vui lòng vào email để nhận mật khẩu mới !");
				kh.setPassword(passwordEncoder.encode(body));
				userdao.save(kh);
				MailInfo mail = new MailInfo();
				mail.setTo(to);
				mail.setSubject(subject);
				mail.setBody("Mật khẩu mới: " +body);
				mailer.queue(mail);
				try {
					mailer.send(mail);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				//return "redirect:/dangnhap";
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message","Tài khoản và email không phù hợp!");
		}
		return "account/login/quenpass";
	}
}
