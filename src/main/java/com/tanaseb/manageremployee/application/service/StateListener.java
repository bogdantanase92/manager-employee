package com.tanaseb.manageremployee.application.service;

import static com.tanaseb.manageremployee.application.config.Constants.entityHeader;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.tanaseb.manageremployee.domain.model.EmployeeEntity;
import com.tanaseb.manageremployee.domain.model.States;
import com.tanaseb.manageremployee.domain.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StateListener implements PersistStateChangeListener {

	private final EmployeeRepository repository;

	public StateListener(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void onPersist(State<String, String> state,
	                      Message<String> message,
	                      Transition<String, String> transition,
	                      StateMachine<String, String> stateMachine) {
		if (message != null && message.getHeaders().containsKey(entityHeader)) {
			EmployeeEntity entity = message.getHeaders().get(entityHeader, EmployeeEntity.class);
			States newState = States.valueOf(state.getId());
			entity.setState(newState);

			log.info("Transition made from: {} to: {} for employee: {}",
					entity.getState().toString(), newState.toString(), entity.getId());
			repository.save(entity);
		}
	}
}
