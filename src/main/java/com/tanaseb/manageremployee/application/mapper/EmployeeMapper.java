package com.tanaseb.manageremployee.application.mapper;

import com.tanaseb.manageremployee.api.model.CreateResponse;
import com.tanaseb.manageremployee.api.model.UpdateResponse;
import com.tanaseb.manageremployee.domain.model.EmployeeEntity;

public class EmployeeMapper {

	public static CreateResponse toCreateResponse(EmployeeEntity entity) {
		return new CreateResponse(
				entity.getId(), entity.getName(), entity.getEmail(),
				entity.getContractType(), entity.getDepartment(), entity.getState()
		);
	}

	public static UpdateResponse toUpdateResponse(EmployeeEntity entity) {
		return new UpdateResponse(entity.getId(), entity.getState());
	}
}
