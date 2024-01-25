package com.assignment.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantity;
	private Long productId;
	private Date issuedDate;
	private Date exportDate;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "receiveId")
	private Receive receive;
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order order;
}
