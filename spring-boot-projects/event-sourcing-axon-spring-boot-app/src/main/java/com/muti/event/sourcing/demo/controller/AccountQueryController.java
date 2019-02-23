package com.muti.event.sourcing.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muti.event.sourcing.demo.service.AccountQueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * AccountQueryController
 *
 * REST Controller to list the events on an aggregate.
 *
 * @author andrea-muti
 *
 */
@RestController
@RequestMapping(value = "/bank-accounts")
@SwaggerDefinition(
	    tags = {
	        @Tag(name = "Account Queries", description = "Account Query Events Endpoint")
	    }
	)
@Api(tags = "Account Queries")
public class AccountQueryController {

	private final AccountQueryService accountQueryService;

	/**
	 * 
	 * @param accountQueryService
	 */
	public AccountQueryController(AccountQueryService accountQueryService) {

		this.accountQueryService = accountQueryService;
	}

	/**
	 * 
	 * @param accountNumber
	 * @return
	 */
	@GetMapping("/{accountNumber}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {

		return accountQueryService.listEventsForAccount(accountNumber);
	}
}