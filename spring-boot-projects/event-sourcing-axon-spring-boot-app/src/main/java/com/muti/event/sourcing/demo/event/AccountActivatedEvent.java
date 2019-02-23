package com.muti.event.sourcing.demo.event;

/**
 * AccountActivatedEvent
 *
 * @author andrea-muti
 *
 */
public class AccountActivatedEvent extends BaseEvent<String> {

	public final Status status;

	/**
	 * 
	 * @param id
	 * @param status
	 */
	public AccountActivatedEvent(String id, Status status) {

		super(id);

		this.status = status;
	}
}