package com.assignment.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(255)")
	private String fullname;
	@Column(columnDefinition = "nvarchar(255)")
	private String phone;
	@Column(columnDefinition = "nvarchar(255)")
	private String destinationPoint;
	@Column(columnDefinition = "nvarchar(255)")
	private String status;
	private Date createDate = new Date();
	@ManyToOne
	@JoinColumn(name = "userCustomer")
	private User userCustomer;
	@ManyToOne
	@JoinColumn(name = "userConfirm")
	private User userConfirm;
	@ManyToOne
	@JoinColumn(name = "tripId")
	private Trip trip;
	@OneToOne(mappedBy = "order")
	private Package packageId;
}
