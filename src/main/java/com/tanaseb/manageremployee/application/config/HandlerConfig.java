package com.tanaseb.manageremployee.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

import com.tanaseb.manageremployee.application.service.StateListener;

@Configuration
public class HandlerConfig {

	private final StateMachine<String, String> stateMachine;
	private final StateListener stateListener;

	public HandlerConfig(StateMachine<String, String> stateMachine, StateListener stateListener) {
		this.stateMachine = stateMachine;
		this.stateListener = stateListener;
	}

	@Bean
	public PersistStateMachineHandler persistStateMachineHandler() {
		PersistStateMachineHandler handler = new PersistStateMachineHandler(stateMachine);
		handler.addPersistStateChangeListener(stateListener);
		return handler;
	}
}
