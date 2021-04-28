package com.tanaseb.manageremployee.api.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequest {
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String contractType;
	@NotNull
	private String department;
}
