package com.muti.event.sourcing.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default AccountQueryService
 *
 * @author andrea-muti
 *
 */
@Service
public class DefaultAccountQueryService implements AccountQueryService {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultAccountQueryService.class);
	
	// provides a method to read events for a particular AggregateId.
    private final EventStore eventStore;
    
    /**
     * 
     * @param eventStore
     */
    public DefaultAccountQueryService(EventStore eventStore) {
        
    	this.eventStore = eventStore;
    }
    
    /**
     * 
     */
    @Override
    public List<Object> listEventsForAccount(String accountNumber) {
    	
    	log.info("Received request to get events of the account # {}", accountNumber);
        
    	return eventStore.readEvents(accountNumber)
    			.asStream()
    			.map(s -> s.getPayload())
    			.collect(Collectors.toList());
    }
}