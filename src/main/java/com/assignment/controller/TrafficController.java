package com.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.entity.Traffic;
import com.assignment.repo.TrafficRepository;

@Controller
public class TrafficController {
	@Autowired
	TrafficRepository TFdao;

	//
	@RequestMapping("/traffic/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {

		Pageable pageable = PageRequest.of(p.orElse(0), 3);
		Page<Traffic> page = TFdao.findAll(pageable);
		model.addAttribute("page", page);
		return "admin/traffic/menu_traffic";
	}

	//
	@GetMapping("/traffic")
	public String index(Model model) {
		Traffic TFitem = new Traffic();
		model.addAttribute("TFitem", TFitem);
		List<Traffic> TFitems = TFdao.findAll();
//		for (Traffic traffic : TFitems) {
//			System.out.println(traffic.getWareHouse().getId());
//		}
		model.addAttribute("TFitems", TFitems);
		// Biến item: buộc lên form
		// Biến items: hiển thị lên bảng
		return "/admin/traffic/menu_traffic";
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
	//

//	@RequestMapping("/search-key")
//	public String searchAndPage(Model model, 
//	@RequestParam("keywords") Optional<String> kw,
//	@RequestParam("p") Optional<Integer> p) {
//		
//		String kwords = kw.orElse(session.get("keywords"));
//		session.set("keywords", kwords);
//		Pageable pageable = PageRequest.of(p.orElse(0), 2);
//		Page<WareHouse> page = Warehousedao.findAllByNameLike("%"+kwords+"%", pageable);
//		model.addAttribute("page", page);
//		return "search-key";
//		}
//	//
//	//
//	@RequestMapping("/warehouse/create")
//	public String create(Model model, WareHouse item ,BindingResult result) 
//			{
//		if(result.hasErrors()) {
//			model.addAttribute("message", "một số testfield chưa dc xử lí!!");
//		}else {
//			Warehousedao.save(item);
//			
//		}
//		
//	return "redirect:/admin/layout/List_warehouse";
//	}
//	//
//	@RequestMapping("/traffic/update")
//	public String update(Traffic item) {
//		TFdao.save(item);
//	retredirect:/urn "traffic/edit/" + item.getId();
//	}
//	//
//	@RequestMappiwarehouseaffic/delete/{id}")
//	public String create(@PathVariable("id") Long id) { 
//		WarehousedaoTFdao.deleteById(id);
//	retredirect:/urn "admin/layout/List_warehouse";
//	}
}
