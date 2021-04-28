package com.tanaseb.manageremployee.api.model;

import javax.validation.constraints.NotNull;

import com.tanaseb.manageremployee.domain.model.Events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
	@NotNull
	private String id;
	@NotNull
	private Events event;
}
