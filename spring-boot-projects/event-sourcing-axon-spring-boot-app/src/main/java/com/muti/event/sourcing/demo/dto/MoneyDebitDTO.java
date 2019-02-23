package com.muti.event.sourcing.demo.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MoneyDebitDTO
 * 
 * DTO for debiting money from an account.
 * 
 * @author andrea-muti
 *
 */
public class MoneyDebitDTO {

	private double debitAmount;
	
    private String currency;
    
    /**
     * 
     * @return
     */
    public double getDebitAmount() {
    	
        return debitAmount;
    }
    
    /**
     * 
     * @param debitAmount
     */
    public void setDebitAmount(double debitAmount) {
    	
        this.debitAmount = debitAmount;
    }
    
    /**
     * 
     * @return
     */
    public String getCurrency() {
    	
        return currency;
    }
    
    /**
     * 
     * @param currency
     */
    public void setCurrency(String currency) {
    	
        this.currency = currency;
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