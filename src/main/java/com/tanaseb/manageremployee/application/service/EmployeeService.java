package com.tanaseb.manageremployee.application.service;

import static com.tanaseb.manageremployee.application.config.Constants.entityHeader;
import static com.tanaseb.manageremployee.application.mapper.EmployeeMapper.toCreateResponse;
import static com.tanaseb.manageremployee.application.mapper.EmployeeMapper.toUpdateResponse;

import java.util.Optional;
import java.util.UUID;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanaseb.manageremployee.api.model.CreateRequest;
import com.tanaseb.manageremployee.api.model.CreateResponse;
import com.tanaseb.manageremployee.api.model.UpdateRequest;
import com.tanaseb.manageremployee.api.model.UpdateResponse;
import com.tanaseb.manageremployee.domain.model.EmployeeEntity;
import com.tanaseb.manageremployee.domain.model.States;
import com.tanaseb.manageremployee.domain.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

	private final EmployeeRepository repository;
	private final StateMachine<String, String> stateMachine;
	private final PersistStateMachineHandler persistStateMachineHandler;

	public EmployeeService(EmployeeRepository repository, StateMachine<String, String> stateMachine, PersistStateMachineHandler persistStateMachineHandler) {
		this.repository = repository;
		this.stateMachine = stateMachine;
		this.persistStateMachineHandler = persistStateMachineHandler;
	}

	public CreateResponse create(CreateRequest request) {
		EmployeeEntity entity = new EmployeeEntity(
				UUID.randomUUID().toString(), request.getName(), request.getEmail(),
				request.getContractType(), request.getDepartment(), States.valueOf(stateMachine.getInitialState().getId())
		);

		return toCreateResponse(repository.save(entity));
	}

	@Transactional
	public UpdateResponse update(UpdateRequest request) {
		Optional<EmployeeEntity> optionalEntity = repository.findById(request.getId());

		return optionalEntity
				.map(entity -> {
					persistStateMachineHandler.handleEventWithState(
							MessageBuilder.withPayload(request.getEvent().name()).setHeader(entityHeader, entity).build(),
							entity.getState().name());

					return toUpdateResponse(entity);
				})
				.orElseThrow(() -> {
					log.error("Employee with id: {} could not be found", request.getId());
					throw new RuntimeException();
				});
	}

}
