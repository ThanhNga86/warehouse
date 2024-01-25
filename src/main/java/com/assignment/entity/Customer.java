package com.assignment.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String email;
	private String fullname;
	private String phone;
	private String address;
	@OneToMany(mappedBy = "customer")
	private List<OrderDetail> listOrderDetails;
}
