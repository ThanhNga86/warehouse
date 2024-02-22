package com.assignment.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Packages")
public class Package implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(4000)")
	private String notes;
	@ManyToOne
	@JoinColumn(name = "warehouseId")
	private WareHouse wareHouse;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "orderId")
	private Order order;
	@OneToMany(mappedBy = "packageId", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Product> listProducts;
}
