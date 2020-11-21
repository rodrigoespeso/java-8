package com.respeso.data.factory;

import java.util.ArrayList;
import java.util.List;

import com.respeso.data.Account;
import com.respeso.data.Holder;

public class Factory {
	
	/*
	 * Returns 5 new accounts shared between 4 different holders
	 */
	public static List<Account> getAccountsWithHolders(){
		
		List<Account> accounts = new ArrayList<>();
		
		List<Holder> holders = getHolders();
		
		accounts.add(new Account("A001", "EUR", 18000L, false, holders.get(0)));
		accounts.add(new Account("A002", "USD", 21000L, false, holders.get(1)));
		accounts.add(new Account("A003-SHARED", "EUR", 61000L, true, holders.get(2), holders.get(3)));
		accounts.add(new Account("A004", "EUR", 33000L, false, holders.get(2)));
		accounts.add(new Account("A005", "EUR", 45000L, false, holders.get(3)));
		
		return accounts;
	}
	
	/*
	 * Returns 4 holders
	 */
	public static List<Holder> getHolders(){
		
		List<Holder> holders = new ArrayList<>();
	
		holders.add(new Holder(1, "Dwayne", "McEnroe", 27, "666141111", "666141222"));
		holders.add(new Holder(2, "Lara", "Smith", 31, "633010333", "602011444"));
		holders.add(new Holder(3, "Jason", "Jackson", 58, "666141555"));
		holders.add(new Holder(4, "Marylin", "Jackson", 58, "666001666"));
		
		return holders;
	}
	
}