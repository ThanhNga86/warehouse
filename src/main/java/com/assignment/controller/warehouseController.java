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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.entity.WareHouse;
import com.assignment.repo.WareHouseRepository;

@Controller
public class warehouseController {
	@Autowired
	WareHouseRepository Warehousedao;
	@Autowired
	SessionService session;
	
	@GetMapping("/warehouses")
	public String addList_warehouse(Model model) {
		WareHouse WHitem = new WareHouse();
		model.addAttribute("WHitem", WHitem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
		return "admin/warehouse/menu_list_warehouse";
	}
	
	@GetMapping("/warehouse/page")
	public String paginate(Model model,
		@RequestParam("p") Optional<Integer> p) {
		 
		Pageable pageable = PageRequest.of(p.orElse(0), 1);
		Page<WareHouse> page = Warehousedao.findAll(pageable);
		model.addAttribute("page", page);
	return "admin/layout/List_warehouse";
	}
	//
//	@GetMapping("/warehouse/addWareHouse")
//	public String index(Model model) {
//		WareHouse WHitem = new WareHouse();
//		model.addAttribute("WHitem", WHitem);
//		List<WareHouse> WHitems = Warehousedao.findAll();
//		model.addAttribute("WHitems", WHitems);
//		// Biến item: buộc lên form
//		// Biến items: hiển thị lên bảng
//	return "admin/warehouse/menu_warehouse";
//	}
	//
	@GetMapping("/warehouse/edit/{id}")
	public String edit(Model model, @PathVariable(name ="id") Long id) {
		
		WareHouse WHitem = Warehousedao.findById(id).get();
		String idWhiem = String.valueOf(WHitem.getId());
		model.addAttribute("idWhiem", idWhiem);
		model.addAttribute("WHitem", WHitem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
	return "admin/warehouse/warehouse";
	}
	
	@GetMapping("/warehouse/edit")
	public String edit(Model model) {
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("idWhiem", WHitems.get(0).getId());
		model.addAttribute("WHitem", WHitems.get(0));
		return "admin/warehouse/warehouse";
	}
	
	@RequestMapping("/search-key")
	public String searchAndPage(Model model, 
	@RequestParam("keywords") Optional<String> kw,
	@RequestParam("p") Optional<Integer> p) {
		
		String kwords = kw.orElse(session.get("keywords"));
		session.set("keywords", kwords);
		Pageable pageable = PageRequest.of(p.orElse(0), 2);
		Page<WareHouse> page = Warehousedao.findAllByNameLike("%"+kwords+"%", pageable);
		model.addAttribute("page", page);
		return "search-key";
		}
//	//
	//
	@RequestMapping("/warehouse/create")
	public String create(Model model, WareHouse WHitem ,BindingResult result) 
			{
		if(result.hasErrors()) {
			model.addAttribute("message", "một số testfield chưa dc xử lí!!");
		}else {
			Warehousedao.save(WHitem);
			
		}
		
	return "redirect:/warehouses";
	}
//	//
	@RequestMapping("/warehouse/update")
	public String update(WareHouse WHitem) {
		System.out.println(WHitem.getId());
		Warehousedao.save(WHitem);
	return "redirect:/warehouse/edit/" + WHitem.getId();
	}
//	//
	@GetMapping("/warehouse/delete")
	public String delete(@RequestParam("id") Long id) {
		Warehousedao.deleteById(id);
	return "redirect:/warehouses";
	}
}
