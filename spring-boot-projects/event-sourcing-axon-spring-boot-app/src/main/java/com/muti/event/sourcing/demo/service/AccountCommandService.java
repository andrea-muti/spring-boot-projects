package com.muti.event.sourcing.demo.service;

import java.util.concurrent.CompletableFuture;

import com.muti.event.sourcing.demo.dto.AccountCreateDTO;
import com.muti.event.sourcing.demo.dto.MoneyCreditDTO;
import com.muti.event.sourcing.demo.dto.MoneyDebitDTO;

/**
 * AccountCommandService.
 * 
 * Service for handling commands.
 *
 * @author andrea-muti
 *
 */
public interface AccountCommandService {
	
	/**
	 * 
	 * @param accountCreateDTO
	 * @return
	 */
	public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
	
	/**
	 * 
	 * @param accountNumber
	 * @param moneyCreditDTO
	 * @return
	 */
	public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
	
	/**
	 * 
	 * @param accountNumber
	 * @param moneyDebitDTO
	 * @return
	 */
	public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}