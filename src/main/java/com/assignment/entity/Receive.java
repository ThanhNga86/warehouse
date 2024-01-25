package com.assignment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receive implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long productId;
	private Integer quantity;
	private Date receiveDate = new Date();
	@Column(columnDefinition = "TEXT")
	private String notes;
	private String status;
	@ManyToOne
	@JoinColumn(name = "supplierId")
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@OneToMany(mappedBy = "receive")
	private List<OrderDetail> listOrderDetails;
}
