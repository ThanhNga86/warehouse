package com.assignment.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.DAO.OrderDAO;
import com.assignment.DAO.PackageDAO;
import com.assignment.DAO.ProductDAO;
import com.assignment.DAO.UserDAO;
import com.assignment.DAO.WareHouseDao;
import com.assignment.entity.Order;
import com.assignment.entity.Package;
import com.assignment.entity.Product;
import com.assignment.entity.Role;
import com.assignment.entity.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	private final WareHouseDao daoWareHouse;
	private final UserDAO daoUser;
	private final PackageDAO daoPackage;
	private final OrderDAO daoOrder;
	private final ProductDAO daoProduct;

	@GetMapping("/package/createOrder")
	public String createOrder(Model model, @RequestParam("packageId") Optional<Long> id) {
		if (id.isPresent()) {
			Optional<Package> packageId = daoPackage.findById(id.get());
			if (packageId.isPresent()) {
				model.addAttribute("packageId", packageId.get());
			}
		}
		model.addAttribute("warehouses", daoWareHouse.findAll());
		return "/admin/order/order";
	}

	@PostMapping("/package/createOrder")
	public ResponseEntity<String> createOrder(@RequestParam("phoneSender") String phoneSender,
			@RequestParam("fullnameSender") String fullnameSender, @RequestParam("phoneReceiver") String phoneReceiver,
			@RequestParam("fullnameReceiver") String fullnameReceiver,
			@RequestParam("addressReceiver") String addressReceiver,
			@RequestParam("fileImage") Optional<List<MultipartFile>> fileImages,
			@RequestParam("nameP") Optional<List<String>> nameP, @RequestParam("massP") Optional<List<Double>> massP,
			@RequestParam("quantityP") Optional<List<Integer>> quantityP, @RequestParam("notes") String notes,
			@RequestParam("warehouse") Long warehouse) {
		User userCustomer = new User(phoneSender, null, fullnameSender, true, null, phoneSender, Role.ROLE_CUSTOMER,
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		daoUser.save(userCustomer);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userConfirm = daoUser.findById(auth.getName()).get();

		Order order = new Order(null, fullnameReceiver, phoneReceiver, addressReceiver, "Chờ xác nhận", new Date(),
				userCustomer, userConfirm, null, null);
		daoOrder.save(order);

		Package packageId = new Package(null, notes, daoWareHouse.findById(warehouse).get(), order, new ArrayList<>());
		daoPackage.save(packageId);

		if (fileImages.isPresent()) {
			String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
			if (!Files.exists(Path.of(path))) {
				try {
					Files.createDirectory(Path.of(path));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			for (int i = 0; i < fileImages.get().size(); i++) {
				String fileName = System.currentTimeMillis() + new Random().nextInt(9) + "_"
						+ fileImages.get().get(i).getOriginalFilename();
				try {
					fileImages.get().get(i).transferTo(new File(path + fileName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

				daoProduct.save(new Product(null, nameP.get().get(i), massP.get().get(i), quantityP.get().get(i),
						fileName, packageId));
			}
		}

		return ResponseEntity.ok("success");
	}

	@GetMapping("/packages")
	public String packages(Model model, @RequestParam("page") Optional<Integer> pageNumber,
			@RequestParam("search") Optional<Long> search) {
		int sizePage = 10;
		if (search.isPresent()) {
			model.addAttribute("packages", daoPackage.findById(search.get()));
		} else {
			Pageable page = PageRequest.of(pageNumber.orElse(1) - 1, sizePage, Sort.by("id").reverse());
			Page<Package> packages = daoPackage.findAll(page);
			long total = packages.getTotalElements();
			int totalPage = (int) (total / sizePage);
			if (total % sizePage != 0) {
				totalPage++;
			}

			model.addAttribute("packages", packages);
			model.addAttribute("total", total);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageNumber", pageNumber.orElse(0));
		}
		return "/admin/order/packages";
	}

	@PostMapping("/delete-package")
	public ResponseEntity<String> deletePackage(@RequestParam("id") Optional<Long> id) {
		if (id.isPresent()) {
			try {
				daoPackage.deleteById(id.get());
				return ResponseEntity.ok("success");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok("error");
			}
		} else {
			return ResponseEntity.ok("error");
		}
	}

	@PostMapping("/accept-package")
	public ResponseEntity<String> acceptPackage(@RequestParam("id") Optional<Long> id) {
		if (id.isPresent()) {
			try {
				Package packageId = daoPackage.findById(id.get()).get();
				String status = packageId.getOrder().getStatus();
				if (status.equalsIgnoreCase("Chờ xác nhận")) {
					packageId.getOrder().setStatus("Đã xác nhận");
				} else if (status.equalsIgnoreCase("Đã xác nhận")) {
					packageId.getOrder().setStatus("Đang vận chuyển");
				} else if (status.equalsIgnoreCase("Đang vận chuyển")) {
					packageId.getOrder().setStatus("Hoàn thành");
				}
				daoOrder.saveAndFlush(packageId.getOrder());
				return ResponseEntity.ok("success");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok("error");
			}
		} else {
			return ResponseEntity.ok("error");
		}
	}

	@PostMapping("/cancel-package")
	public ResponseEntity<String> cancelPackage(@RequestParam("id") Optional<Long> id) {
		if (id.isPresent()) {
			try {
				Package packageId = daoPackage.findById(id.get()).get();
				packageId.getOrder().setStatus("Bị hủy");
				daoOrder.saveAndFlush(packageId.getOrder());
				return ResponseEntity.ok("success");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok("error");
			}
		} else {
			return ResponseEntity.ok("error");
		}
	}

	@PostMapping("/restore-package")
	public ResponseEntity<String> restorePackage(@RequestParam("id") Optional<Long> id) {
		if (id.isPresent()) {
			try {
				Package packageId = daoPackage.findById(id.get()).get();
				String status = packageId.getOrder().getStatus();
				if (status.equalsIgnoreCase("Hoàn thành")) {
					packageId.getOrder().setStatus("Đang vận chuyển");
				} else if (status.equalsIgnoreCase("Đang vận chuyển")) {
					packageId.getOrder().setStatus("Đã xác nhận");
				} else if (status.equalsIgnoreCase("Đã xác nhận")) {
					packageId.getOrder().setStatus("Chờ xác nhận");
				} else if (status.equalsIgnoreCase("Bị hủy")) {
					packageId.getOrder().setStatus("Chờ xác nhận");
				}
				daoOrder.saveAndFlush(packageId.getOrder());
				return ResponseEntity.ok("success");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok("error");
			}
		} else {
			return ResponseEntity.ok("error");
		}
	}

	@GetMapping("/detail-package")
	public ResponseEntity<String> detailPackage(@RequestParam("id") Optional<Long> id) {
		StringBuilder sb = new StringBuilder();
		if (id.isPresent()) {
			try {
				Package packageId = daoPackage.findById(id.get()).get();
				sb.append("<div class=\"infoUserPackage\">\r\n"
						+ "								<div class=\"formLeft\">\r\n"
						+ "									<h4>Người gửi</h4>\r\n"
						+ "									<div>Số điện thoại: "
						+ packageId.getOrder().getUserCustomer().getPhone() + "</div>\r\n"
						+ "									<div>Tên người gửi: "
						+ packageId.getOrder().getUserCustomer().getFullname() + "</div>\r\n"
						+ "								</div>\r\n"
						+ "								<div class=\"formRight\">\r\n"
						+ "									<h4>Người nhận</h4>\r\n"
						+ "									<div>Số điện thoại: " + packageId.getOrder().getPhone()
						+ "</div>\r\n" + "									<div>Tên người nhận: "
						+ packageId.getOrder().getFullname() + "</div>\r\n"
						+ "									<div>Địa chỉ người nhận: "
						+ packageId.getOrder().getDestinationPoint() + "</div>\r\n"
						+ "								</div>\r\n" + "							</div>\r\n"
						+ "							\r\n" + "							<br>\r\n"
						+ "							<h4>Sản phẩm</h4>\r\n"
						+ "							<div class=\"productPackage\">\r\n"
						+ "								<table class=\"table table-bordered\">\r\n"
						+ "									<tr>\r\n"
						+ "										<th>Hình ảnh</th>\r\n"
						+ "										<th>Tên sản phẩm</th>\r\n"
						+ "										<th>Khối lượng</th>\r\n"
						+ "										<th>Số lượng</th>\r\n"
						+ "									</tr>\r\n");
				for (Product product : packageId.getListProducts()) {
					sb.append("									<tr>\r\n"
							+ "										<td><img width=\"60\" height=\"60\" src=\"/images/"
							+ product.getImage() + "\"></td>\r\n" + "										<td>"
							+ product.getName() + "</td>\r\n" + "										<td>"
							+ product.getMass() + "</td>\r\n" + "										<td>"
							+ product.getQuantity() + "</td>\r\n" + "									</tr>\r\n");
				}
				sb.append("								</table>\r\n" + "							</div>\r\n"
						+ "							<h4>Thông tin khác</h4>\r\n"
						+ "							<div>Ghi chú: " + packageId.getNotes() + "</div>\r\n");
				return ResponseEntity.ok(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok("error");
			}
		} else {
			return ResponseEntity.ok("error");
		}
	}

	@PostMapping("/package/updateOrder")
	public ResponseEntity<String> updateOrder(@RequestParam("packageId") Optional<Long> id,
			@RequestParam("phoneSender") String phoneSender, @RequestParam("fullnameSender") String fullnameSender,
			@RequestParam("phoneReceiver") String phoneReceiver,
			@RequestParam("fullnameReceiver") String fullnameReceiver,
			@RequestParam("addressReceiver") String addressReceiver,
			@RequestParam("fileImage") Optional<List<MultipartFile>> fileImages,
			@RequestParam("imageName") Optional<List<String>> imageNames,
			@RequestParam("nameP") Optional<List<String>> nameP, @RequestParam("massP") Optional<List<Double>> massP,
			@RequestParam("quantityP") Optional<List<Integer>> quantityP, @RequestParam("notes") String notes,
			@RequestParam("warehouse") Long warehouse) {
		if (id.isPresent()) {
			Package packageId = daoPackage.findById(id.get()).get();

			User userCustomer = daoUser.findById(packageId.getOrder().getUserCustomer().getUsername()).get();
			userCustomer.setPhone(phoneSender);
			userCustomer.setFullname(fullnameSender);
			daoUser.saveAndFlush(userCustomer);

			Order order = daoOrder.findById(packageId.getOrder().getId()).get();
			order.setPhone(phoneReceiver);
			order.setFullname(fullnameReceiver);
			order.setDestinationPoint(addressReceiver);

			packageId.setNotes(notes);
			packageId.setWareHouse(daoWareHouse.findById(warehouse).get());
			daoPackage.saveAndFlush(packageId);

			for (Product p : packageId.getListProducts()) {
				daoProduct.deleteById(p.getId());
			}

			if (fileImages.isPresent()) {
				String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";

				for (int i = 0; i < fileImages.get().size(); i++) {
					String fileName = fileImages.get().get(i).getOriginalFilename();
					try {
						fileImages.get().get(i).transferTo(new File(path + fileName));
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}

			for (int i = 0; i < imageNames.get().size(); i++) {
				daoProduct.save(new Product(null, nameP.get().get(i), massP.get().get(i), quantityP.get().get(i),
						imageNames.get().get(i), packageId));
			}
		}

		return ResponseEntity.ok("success");
	}
}
