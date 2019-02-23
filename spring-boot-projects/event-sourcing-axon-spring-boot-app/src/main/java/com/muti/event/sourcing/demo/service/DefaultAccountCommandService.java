package com.muti.event.sourcing.demo.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.muti.event.sourcing.demo.command.CreateAccountCommand;
import com.muti.event.sourcing.demo.command.CreditMoneyCommand;
import com.muti.event.sourcing.demo.command.DebitMoneyCommand;
import com.muti.event.sourcing.demo.dto.AccountCreateDTO;
import com.muti.event.sourcing.demo.dto.MoneyCreditDTO;
import com.muti.event.sourcing.demo.dto.MoneyDebitDTO;

/**
 * Default AccountCommandService
 *
 * @author andrea-muti
 *
 */
@Service
public class DefaultAccountCommandService implements AccountCommandService {

	private static final Logger log = LoggerFactory.getLogger(DefaultAccountCommandService.class);

	private final CommandGateway commandGateway;

	/**
	 * 
	 * @param commandGateway
	 */
	public DefaultAccountCommandService(CommandGateway commandGateway) {

		this.commandGateway = commandGateway;
	}

	@Override
	public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {

		log.info("Received a Create Account request for account {}", accountCreateDTO.toString());

		return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
	}

	@Override
	public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {

		log.info("Received request to Credit Money to the account # {}", accountNumber);

		return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
	}

	@Override
	public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {

		log.info("Received request to Debit money from the account # {}", accountNumber);

		return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
	}
}