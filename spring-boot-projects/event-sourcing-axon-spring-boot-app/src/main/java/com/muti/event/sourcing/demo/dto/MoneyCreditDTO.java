package com.muti.event.sourcing.demo.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MoneyCreditDTO
 * 
 * DTO for crediting money to an account.
 * 
 * @author andrea-muti
 *
 */
public class MoneyCreditDTO {

	private double creditAmount;

	private String currency;

	/**
	 * 
	 * @return
	 */
	public double getCreditAmount() {

		return creditAmount;
	}

	/**
	 * 
	 * @param creditAmount
	 */
	public void setCreditAmount(double creditAmount) {

		this.creditAmount = creditAmount;
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