package com.muti.event.sourcing.demo.command;

/**
 * CreateAccountCommand
 *
 * @author andrea-muti
 *
 */
public class CreateAccountCommand extends BaseCommand<String> {

	public final double accountBalance;
	public final String currency;

	/**
	 * 
	 * @param id
	 * @param accountBalance
	 * @param currency
	 */
	public CreateAccountCommand(String id, double accountBalance, String currency) {

		super(id);

		this.accountBalance = accountBalance;
		this.currency = currency;
	}
}