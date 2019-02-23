package com.muti.event.sourcing.demo.event;

/**
 * MoneyDebitedEvent
 *
 * @author andrea-muti
 *
 */
public class MoneyDebitedEvent extends BaseEvent<String> {

	public final double debitAmount;
	public final String currency;

	/**
	 * 
	 * @param id
	 * @param debitAmount
	 * @param currency
	 */
	public MoneyDebitedEvent(String id, double debitAmount, String currency) {

		super(id);

		this.debitAmount = debitAmount;
		this.currency = currency;
	}
}