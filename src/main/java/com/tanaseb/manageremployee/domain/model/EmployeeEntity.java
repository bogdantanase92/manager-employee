package com.tanaseb.manageremployee.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "employee")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmployeeEntity {

	@Id
	private String id;
	private String name;
	private String email;
	private String contractType;
	private String department;
	@Enumerated(value = EnumType.STRING)
	private States state;
}
