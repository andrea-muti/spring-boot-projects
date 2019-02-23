package com.muti.event.sourcing.demo.event;

/**
 * AccountCreatedEvent
 *
 * @author andrea-muti
 *
 */
public class AccountCreatedEvent extends BaseEvent<String> {

	public final double accountBalance;
	public final String currency;

	/**
	 * 
	 * @param id
	 * @param accountBalance
	 * @param currency
	 */
	public AccountCreatedEvent(String id, double accountBalance, String currency) {

		super(id);

		this.accountBalance = accountBalance;
		this.currency = currency;
	}
}