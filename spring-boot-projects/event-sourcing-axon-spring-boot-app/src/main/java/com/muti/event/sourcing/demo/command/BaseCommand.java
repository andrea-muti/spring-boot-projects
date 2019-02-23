package com.muti.event.sourcing.demo.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * BaseCommand
 * 
 * Commands are user-initiated actions that can change the state of your aggregate.
 * 
 * @author andrea-muti
 *
 * @param <T> type for the ID of the entity
 */
public class BaseCommand<T> {

	@TargetAggregateIdentifier	// Axon specific requirement to identify the aggregate instance.
	public final T id;

	public BaseCommand(T id) {
		
		this.id = id;
	}
}