package com.tanaseb.manageremployee.api.model;

import com.tanaseb.manageremployee.domain.model.States;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponse {
	private String id;
	private States state;
}
