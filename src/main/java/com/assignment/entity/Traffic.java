package com.assignment.entity;

import java.io.Serializable;
import java.util.Date;

<<<<<<< HEAD
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
=======
<<<<<<< HEAD

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;

=======
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
>>>>>>> 1b032285f61fb40100030f1f39afd398ce2a4c23
>>>>>>> 4795f699701d821455891a8edca979cbb65a144d
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
@Table
public class Traffic implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(255)")
	private String name;
	@Column(columnDefinition = "nvarchar(255)")
	private String capacity;
	@Column(columnDefinition = "nvarchar(255)")
	private String status;
	@Column(columnDefinition = "nvarchar(255)")
	private String type;
	private Date dateBuy;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "tripId")
	private Trip trip;
	@ManyToOne
	@JoinColumn(name = "warehouseId")
	private WareHouse wareHouse;
}
