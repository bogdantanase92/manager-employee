package com.tanaseb.manageremployee.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tanaseb.manageremployee.ManagerEmployeeApplication;
import com.tanaseb.manageremployee.api.model.CreateRequest;
import com.tanaseb.manageremployee.api.model.CreateResponse;
import com.tanaseb.manageremployee.api.model.UpdateRequest;
import com.tanaseb.manageremployee.api.model.UpdateResponse;
import com.tanaseb.manageremployee.domain.model.Events;
import com.tanaseb.manageremployee.domain.model.States;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ManagerEmployeeApplication.class})
class EmployeeServiceTest {

	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String CONTRACT_TYPE = "contractType";
	public static final String DEPARTMENT = "department";

	@Autowired
	private EmployeeService employeeService;

	@Test
	void initiateState() {
		CreateResponse actual = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));

		Assertions.assertNotNull(actual.getId());
		Assertions.assertEquals(States.ADDED, actual.getState());
		Assertions.assertEquals(NAME, actual.getName());
		Assertions.assertEquals(EMAIL, actual.getEmail());
		Assertions.assertEquals(CONTRACT_TYPE, actual.getContractType());
		Assertions.assertEquals(DEPARTMENT, actual.getDepartment());
	}

	@Test
	void transitFromAddedToInCheck() {
		CreateResponse createResponse = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));
		UpdateResponse actual = employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));

		Assertions.assertEquals(createResponse.getId(), actual.getId());
		Assertions.assertEquals(States.IN_CHECK, actual.getState());
	}

	@Test
	void transitFromInCheckToAdded() {
		CreateResponse createResponse = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		UpdateResponse actual = employeeService.update(new UpdateRequest(createResponse.getId(), Events.PREVIOUS));

		Assertions.assertEquals(createResponse.getId(), actual.getId());
		Assertions.assertEquals(States.ADDED, actual.getState());
	}

	@Test
	void transitFromInCheckToApproved() {
		CreateResponse createResponse = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		UpdateResponse actual = employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));

		Assertions.assertEquals(createResponse.getId(), actual.getId());
		Assertions.assertEquals(States.APPROVED, actual.getState());
	}

	@Test
	void transitFromApprovedToInCheck() {
		CreateResponse createResponse = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		UpdateResponse actual = employeeService.update(new UpdateRequest(createResponse.getId(), Events.PREVIOUS));

		Assertions.assertEquals(createResponse.getId(), actual.getId());
		Assertions.assertEquals(States.IN_CHECK, actual.getState());
	}

	@Test
	void transitFromApprovedToActive() {
		CreateResponse createResponse = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		UpdateResponse actual = employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));

		Assertions.assertEquals(createResponse.getId(), actual.getId());
		Assertions.assertEquals(States.ACTIVE, actual.getState());
	}

	@Test
	void transitFromActiveToApproved() {
		CreateResponse createResponse = employeeService.create(new CreateRequest(NAME, EMAIL, CONTRACT_TYPE, DEPARTMENT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		employeeService.update(new UpdateRequest(createResponse.getId(), Events.NEXT));
		UpdateResponse actual = employeeService.update(new UpdateRequest(createResponse.getId(), Events.PREVIOUS));

		Assertions.assertEquals(createResponse.getId(), actual.getId());
		Assertions.assertEquals(States.APPROVED, actual.getState());
	}
}