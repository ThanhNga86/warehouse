package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.DAO.UserDAO;
import com.assignment.entity.MailInfo;
import com.assignment.service.MailerService;

@Controller
public class doiPassController {
	@Autowired
	UserDAO userdao;
//	@Autowired
//	MailerService mailer;
	
	@RequestMapping("/account/change-password")
	public String doipass(Model model) {
//		MailInfo mail = new MailInfo();
//		model.addAttribute("mail",mail);
		return "account/login/doipass";
	}
	
	
}
