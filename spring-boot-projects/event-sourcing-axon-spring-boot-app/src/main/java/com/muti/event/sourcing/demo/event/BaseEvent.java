package com.muti.event.sourcing.demo.event;

/**
 * BaseEvent
 *
 * events are the actual changing of the state of the aggregate.
 *
 * @author andrea-muti
 *
 * @param <T>
 */
public class BaseEvent<T> {

	public final T id;

	public BaseEvent(T id) {

		this.id = id;
	}
}