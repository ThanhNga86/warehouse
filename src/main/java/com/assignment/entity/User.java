package com.assignment.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "Users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	private String password;
	@Column(columnDefinition = "nvarchar(255)")
	private String fullname;
	private boolean active;
	private String email;
	private String phone;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToMany(mappedBy = "user")
	private List<WareHouse> listWareHouses;
	@OneToMany(mappedBy = "userCustomer")
	private List<Order> listOrder1;
	@OneToMany(mappedBy = "userConfirm")
	private List<Order> listOrder2;
	@OneToMany(mappedBy = "user")
	private List<Trip> listTrips;
}
