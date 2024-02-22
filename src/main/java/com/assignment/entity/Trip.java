package com.assignment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table
public class Trip implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(255)")
	private String type;
	@Column(columnDefinition = "nvarchar(255)")
	private String status;
	private Date timeStart = new Date();
	private Date timeEnd;
	@Column(columnDefinition = "nvarchar(500)")
	private String travelDistain;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@OneToMany(mappedBy = "trip")
	private List<Order> listOrders;
	@OneToOne(mappedBy = "trip", cascade = CascadeType.REMOVE)
	private Traffic traffic;
}
