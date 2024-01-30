package com.assignment.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	Serializable groups;
	Double sum;
	Long count;
}
