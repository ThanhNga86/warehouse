package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.DAO.DasboardDAO;
import com.assignment.DAO.PackageDAO;
import com.assignment.DAO.TrafficDAO;
import com.assignment.DAO.UserDAO;
import com.assignment.DAO.WareHouseDao;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private DasboardDAO dasboardDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TrafficDAO trafficDAO;
	@Autowired
	private PackageDAO packageDAO;
	@Autowired
	private WareHouseDao wareHouseDao;

	@GetMapping("")
	public String dashboard(Model model) {

		long totalOrders = dasboardDAO.count();
		model.addAttribute("totalOrders", totalOrders);

		long totalCustormers = userDAO.count();
		model.addAttribute("totalCustormers", totalCustormers);

		long totalPT = trafficDAO.count();
		model.addAttribute("totalPT", totalPT);

		long totalDg = packageDAO.count();
		model.addAttribute("totalDg", totalDg);

		long totalKh = wareHouseDao.count();
		model.addAttribute("totalKh", totalKh);
		return "/admin/dashboard/dashboard";
	}

}
