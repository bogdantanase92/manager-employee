package com.tanaseb.manageremployee.api.model;

import com.tanaseb.manageremployee.domain.model.States;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponse {
	private String id;
	private String name;
	private String email;
	private String contractType;
	private String department;
	private States state;
}
