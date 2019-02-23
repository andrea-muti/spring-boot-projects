package com.muti.event.sourcing.demo.event;

/**
 * AccountHeldEvent
 *
 * @author andrea-muti
 *
 */
public class AccountHeldEvent extends BaseEvent<String> {

	public final Status status;

	/**
	 * 
	 * @param id
	 * @param status
	 */
	public AccountHeldEvent(String id, Status status) {

		super(id);

		this.status = status;
	}
}