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
	
	//
	@GetMapping("/warehouse/addWareHouse")
	public String index(Model model) {
		WareHouse WHitem = new WareHouse();
		model.addAttribute("WHitem", WHitem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
		// Biến item: buộc lên form
		// Biến items: hiển thị lên bảng
	return "admin/warehouse/menu_warehouse";
	}
	@GetMapping("/warehouses")
	public String indexs(Model model) {
		WareHouse WHitem = new WareHouse();
		model.addAttribute("WHitem", WHitem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
		// Biến item: buộc lên form
		// Biến items: hiển thị lên bảng
	return "admin/warehouse/menu_List_warehouse";
	}
	//
	@RequestMapping("/warehouse/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		WareHouse WHitem = Warehousedao.findById(id).get();
		String idWhiem = String.valueOf(WHitem.getId());
		model.addAttribute("WHitem", idWhiem);
		List<WareHouse> WHitems = Warehousedao.findAll();
		model.addAttribute("WHitems", WHitems);
	return "admin/layout/warehouse";
	}
	//
	//
	
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
	//
	//
	@RequestMapping("/warehouse/create")
	public String create(Model model, WareHouse item ,BindingResult result) 
			{
		if(result.hasErrors()) {
			model.addAttribute("message", "một số testfield chưa dc xử lí!!");
		}else {
			Warehousedao.save(item);
			
		}
		
	return "redirect:/admin/layout/List_warehouse";
	}
	//
	@RequestMapping("/warehouse/update")
	public String update(WareHouse item) {
		Warehousedao.save(item);
	return "redirect:/warehouse/edit/" + item.getId();
	}
	//
	@RequestMapping("/warehouse/delete/{id}")
	public String create(@PathVariable("id") Long id) {
		Warehousedao.deleteById(id);
	return "redirect:/admin/layout/List_warehouse";
	}
}
