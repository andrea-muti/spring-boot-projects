package com.muti.event.sourcing.demo.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.muti.event.sourcing.demo.command.CreateAccountCommand;
import com.muti.event.sourcing.demo.command.CreditMoneyCommand;
import com.muti.event.sourcing.demo.command.DebitMoneyCommand;
import com.muti.event.sourcing.demo.event.AccountActivatedEvent;
import com.muti.event.sourcing.demo.event.AccountCreatedEvent;
import com.muti.event.sourcing.demo.event.AccountHeldEvent;
import com.muti.event.sourcing.demo.event.MoneyCreditedEvent;
import com.muti.event.sourcing.demo.event.MoneyDebitedEvent;
import com.muti.event.sourcing.demo.event.Status;

/**
 * Account entity
 *
 * @author andrea-muti
 *
 */
@Aggregate	// tells Axon that this entity will be managed by Axon
public class AccountAggregate {

	@AggregateIdentifier	// identifying a particular instance of the Aggregate (i.e. ID)
	private String id;
	private double accountBalance;
	private String currency;
	private String status;

	/**
	 * Default Constructor
	 * 
	 * Axon framework required a no-args constructor.
	 */
	public AccountAggregate() {

		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the accountBalance
	 */
	public double getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Constructor
	 * 
	 * Creates a new AccountAggregate instance from the provided 
	 * CreateAccount Command.
	 *
	 * @param createAccountCommand a {@linkplain CreateAccountCommand} object
	 */
	@CommandHandler
	public AccountAggregate(CreateAccountCommand createAccountCommand) {

		AggregateLifecycle.apply(
				new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance, createAccountCommand.currency));
	}
	
	/**
	 * Handler for the "Account Created" Event
	 * 
	 * the Aggregate Identifier must be set in the first method annotated with @EventSourcingHandler
	 *
	 * @param accountCreatedEvent an {@linkplain AccountCreatedEvent} object
	 */
	@EventSourcingHandler
	protected void on(AccountCreatedEvent accountCreatedEvent) {
		
		this.id = accountCreatedEvent.id;
		this.accountBalance = accountCreatedEvent.accountBalance;
		this.currency = accountCreatedEvent.currency;
		this.status = String.valueOf(Status.CREATED);
		
		AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
	}
	
	/**
	 * Handler for the "Account Activated" Event.
	 *
	 * @param accountActivatedEvent an {@linkplain AccountActivatedEvent} event
	 */
	@EventSourcingHandler
	protected void on(AccountActivatedEvent accountActivatedEvent) {
	
		this.status = String.valueOf(accountActivatedEvent.status);
	}
	
	/**
	 * Handler for the "Credit Money" Command.
	 *
	 * @param creditMoneyCommand a {@linkplain CreditMoneyCommand} command
	 */
	@CommandHandler
	protected void on(CreditMoneyCommand creditMoneyCommand) {
	
		AggregateLifecycle.apply(
				new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount, creditMoneyCommand.currency));
	}
	
	/**
	 * Handler for the "Money Credited" Event.
	 *
	 * @param moneyCreditedEvent a {@linkplain MoneyCreditedEvent} event
	 */
	@EventSourcingHandler
	protected void on(MoneyCreditedEvent moneyCreditedEvent) {
		
		if (this.accountBalance < 0 & (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0){
			
			AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
		}
		
		this.accountBalance += moneyCreditedEvent.creditAmount;
	}
	
	/**
	 * Handler for the "Debit Money" Command.
	 *
	 * @param debitMoneyCommand a {@linkplain DebitMoneyCommand} command.
	 */
	@CommandHandler
	protected void on(DebitMoneyCommand debitMoneyCommand) {
	
		AggregateLifecycle.apply(
				new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
	}
	
	/**
	 * Handler for the "Money Debited" Event.
	 *
	 * @param moneyDebitedEvent a {@linkplain MoneyDebitedEvent} event.
	 */
	@EventSourcingHandler
	protected void on(MoneyDebitedEvent moneyDebitedEvent) {
		
		if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0){
			
			AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
		}
		
		this.accountBalance -= moneyDebitedEvent.debitAmount;
	}
	
	/**
	 * Handler for the "Account Held" Event.
	 *
	 * @param accountHeldEvent an {@linkplain AccountHeldEvent} event. 
	 */
	@EventSourcingHandler
	protected void on(AccountHeldEvent accountHeldEvent) {
		
		this.status = String.valueOf(accountHeldEvent.status);
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
	
		return HashCodeBuilder.reflectionHashCode(this, true);
	}

	@Override
	public boolean equals(Object obj) {
		
		return EqualsBuilder.reflectionEquals(this, obj, true);
	}
}