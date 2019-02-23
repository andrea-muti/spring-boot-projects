package com.muti.event.sourcing.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muti.event.sourcing.demo.dto.AccountCreateDTO;
import com.muti.event.sourcing.demo.dto.MoneyCreditDTO;
import com.muti.event.sourcing.demo.dto.MoneyDebitDTO;
import com.muti.event.sourcing.demo.service.AccountCommandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * AccountCommand Controller
 * 
 * REST Controller to handle the commands.
 *
 * @author andrea-muti
 *
 */
@RestController
@RequestMapping(value = "/bank-accounts")
@SwaggerDefinition(
	    tags = {
	        @Tag(name = "Account Commands", description = "Account Commands Related Endpoints")
	    }
	)
@Api(tags = "Account Commands")
public class AccountCommandController {
	
	private final AccountCommandService accountCommandService;
	
	/**
	 * 
	 * @param accountCommandService
	 */
	public AccountCommandController(AccountCommandService accountCommandService) {

		this.accountCommandService = accountCommandService;
	}

	@PostMapping
	public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO) {

		return accountCommandService.createAccount(accountCreateDTO);
	}

	@PutMapping(value = "/credits/{accountNumber}")
	public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
			@RequestBody MoneyCreditDTO moneyCreditDTO) {

		return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
	}

	@PutMapping(value = "/debits/{accountNumber}")
	public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
			@RequestBody MoneyDebitDTO moneyDebitDTO) {

		return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
	}
}