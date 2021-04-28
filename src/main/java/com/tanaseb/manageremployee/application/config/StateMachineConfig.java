package com.tanaseb.manageremployee.application.config;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.tanaseb.manageremployee.domain.model.Events;
import com.tanaseb.manageremployee.domain.model.States;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

	@Override
	public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
		Set<String> stringStates = new HashSet<>();
		EnumSet.allOf(States.class)
				.forEach(entity -> stringStates.add(entity.name()));
		states
				.withStates()
				.initial(States.ADDED.name())
				.states(stringStates);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
		transitions
				.withExternal().source(States.ADDED.name()).target(States.IN_CHECK.name()).event(Events.NEXT.name())
				.and()
				.withExternal().source(States.IN_CHECK.name()).target(States.ADDED.name()).event(Events.PREVIOUS.name())
				.and()
				.withExternal().source(States.IN_CHECK.name()).target(States.APPROVED.name()).event(Events.NEXT.name())
				.and()
				.withExternal().source(States.APPROVED.name()).target(States.IN_CHECK.name()).event(Events.PREVIOUS.name())
				.and()
				.withExternal().source(States.APPROVED.name()).target(States.ACTIVE.name()).event(Events.NEXT.name())
				.and()
				.withExternal().source(States.ACTIVE.name()).target(States.APPROVED.name()).event(Events.PREVIOUS.name());
	}
}


