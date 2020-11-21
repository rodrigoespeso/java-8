package com.respeso.data;

import java.util.Arrays;
import java.util.List;

public class Account{
	
	private String name;
	private String currency;
	private Long balance;
	private Boolean isShared;
	private List<Holder> holders;
	
	public Account(String name, String currency, Long balance, Boolean isShared, List<Holder> holders) {
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.isShared = isShared;
		this.holders = holders;
	}
	
	public Account(String name, String currency, Long balance, Boolean isShared, Holder... holder) {
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.isShared = isShared;
		this.holders = Arrays.asList(holder);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Boolean getIsShared() {
		return isShared;
	}

	public void setIsShared(Boolean isShared) {
		this.isShared = isShared;
	}

	public List<Holder> getHolders() {
		return holders;
	}

	public void setHolders(List<Holder> holders) {
		this.holders = holders;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", currency=" + currency + ", balance=" + balance + ", isShared=" + isShared
				+ ", holders=..." /*holders*/ + "]";
	}
	
}
