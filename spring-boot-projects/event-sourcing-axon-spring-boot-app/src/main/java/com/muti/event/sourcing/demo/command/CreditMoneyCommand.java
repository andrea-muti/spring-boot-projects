package com.muti.event.sourcing.demo.command;

/**
 * CreditMoneyCommand
 *
 * @author andrea-muti
 *
 */
public class CreditMoneyCommand extends BaseCommand<String> {

	public final double creditAmount;
	public final String currency;

	/**
	 * 
	 * @param id
	 * @param creditAmount
	 * @param currency
	 */
	public CreditMoneyCommand(String id, double creditAmount, String currency) {

		super(id);

		this.creditAmount = creditAmount;
		this.currency = currency;
	}
}