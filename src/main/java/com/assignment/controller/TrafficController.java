package com.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.entity.Traffic;
import com.assignment.entity.Trip;
import com.assignment.entity.WareHouse;
import com.assignment.repo.TrafficRepository;
import com.assignment.repo.TripRepository;
import com.assignment.repo.WareHouseRepository;



@Controller
public class TrafficController {
	@Autowired
	TrafficRepository TFdao;

	@Autowired
	WareHouseRepository WHdao;

	@Autowired
	TripRepository Tdao;
	
	@Autowired
	SessionService session;

	//
//	@RequestMapping("/traffic/page")
//	public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
//
//		Pageable pageable = PageRequest.of(p.orElse(0), 3);
//		Page<Traffic> page = TFdao.findAll(pageable);
//		model.addAttribute("TFitems", page);
//		return "admin/traffic/menu_traffic";
//	}

	//
	@GetMapping("/traffic")
	public String index(Model model) {
		Traffic TFitem = new Traffic();
		model.addAttribute("TFitem", TFitem);
		List<Traffic> TFitems = TFdao.findAll();
		model.addAttribute("TFitems", TFitems);
		// Biến item: buộc lên form
		// Biến items: hiển thị lên bảng
		return "admin/traffic/menu_traffic";
	}



	//
	@RequestMapping("/traffic/edit/{id}")
	public String edit(Model model, @PathVariable(name = "id") Long id) {

		Traffic TFitem = TFdao.findById(id).get();
		String idWhiem = String.valueOf(TFitem.getId());
		model.addAttribute("TFitem", TFitem);
		List<Traffic> TFitems = TFdao.findAll();
		model.addAttribute("TFitems", TFitems);
		return "admin/traffic/menu_traffic";
	}
	//


	
	@RequestMapping("/searchTF")
	public String search(Model model,
	@RequestParam("id") Long id	) {
		
		Optional<Traffic> TFitems = TFdao.findById(id);
		Traffic TFitem = TFdao.findById(id).get();
		model.addAttribute("TFitem", TFitem);
		model.addAttribute("TFitems", TFitems);
		
		
		return "redirect:/traffic/edit/" + TFitem.getId();
		}
	
	//
	//
	@RequestMapping("/traffic/create")
	public String create(Model model,Traffic TFitem, BindingResult result) {
		
			
				TFdao.save(TFitem);
				
			model.addAttribute("message", "create thành công");
	

		return "redirect:/traffic";
	}

	//
	@RequestMapping("/traffic/update")
	public String update(Traffic TFitem) {
		System.out.println(TFitem.getId());
		TFdao.save(TFitem);
		return "redirect:/traffic/edit/" + TFitem.getId();
	}

	//
	@RequestMapping("/traffic/delete")
	public String delete(@RequestParam("id") Long id) {
		TFdao.deleteById(id);
		return "redirect:/traffic";
	}

}
