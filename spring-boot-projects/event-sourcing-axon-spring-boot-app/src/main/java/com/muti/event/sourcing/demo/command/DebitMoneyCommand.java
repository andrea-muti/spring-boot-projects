package com.muti.event.sourcing.demo.command;

/**
 * DebitMoneyCommand
 *
 * @author andrea-muti
 *
 */
public class DebitMoneyCommand extends BaseCommand<String> {

	public final double debitAmount;
	public final String currency;

	/**
	 * 
	 * @param id
	 * @param debitAmount
	 * @param currency
	 */
	public DebitMoneyCommand(String id, double debitAmount, String currency) {

		super(id);

		this.debitAmount = debitAmount;
		this.currency = currency;
	}
}