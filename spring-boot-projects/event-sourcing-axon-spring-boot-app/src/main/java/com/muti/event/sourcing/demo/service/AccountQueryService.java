package com.muti.event.sourcing.demo.service;

import java.util.List;

/**
 * AccountQueryService
 * 
 * Service for fetching events on account
 * 
 * @author andrea-muti
 *
 */
public interface AccountQueryService {
	
	/**
	 * 
	 * @param accountNumber
	 * @return
	 */
    public List<Object> listEventsForAccount(String accountNumber);
}