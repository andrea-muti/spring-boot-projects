package com.muti.event.sourcing.demo.event;

/**
 * MoneyCreditedEvent
 *
 * @author andrea-muti
 *
 */
public class MoneyCreditedEvent extends BaseEvent<String> {

	public final double creditAmount;
	public final String currency;

	/**
	 * 
	 * @param id
	 * @param creditAmount
	 * @param currency
	 */
	public MoneyCreditedEvent(String id, double creditAmount, String currency) {

		super(id);

		this.creditAmount = creditAmount;
		this.currency = currency;
	}
}