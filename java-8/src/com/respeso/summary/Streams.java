package com.respeso.summary;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.respeso.data.Account;
import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

/** 
 * Java 8 Streams. Use examples of:
 * map()
 * flatMap()
 * distinct()
 * count()
 * sorted()
 * sorted(Comparator)
 * filter()
 * reduce()  
 * Map + Filter + Reduce Pattern
 * limit() and skip()
 * 
 * @author Rodrigo Espeso
 */
public class Streams {

	public static void main(String[] args) {
		map_getAccountNames();
		flatMap_getAllHolderPhones();
		distinct_getDistinctHolderAges();
		sortedDefault_getSorteredAccountBalances();
		sortedWithComparator_getSorteredAccountsByBalance();
		filter_getFilteredAccounts();
		reduce_getTotalBalance();
		mapFilterAndReducePattern_getTotalBalanceOfEURAccounts();
		limitAndSkip_getSpecificPhones();
	}

	private static void map_getAccountNames() {
		List<Account> accountsWithHolders = Factory.getAccountsWithHolders();
		
		List<String> accountNames = accountsWithHolders.stream()
			.map(Account::getName)
			.collect(Collectors.toList());
		
		System.out.println("map():\n\t List of account names: "+accountNames);
		
	}
	
	private static void flatMap_getAllHolderPhones() {
		List<List<String>> phones = Factory.getHolders().stream()
				.map(Holder::getPhoneNumbers)
				.collect(Collectors.toList());
				
		// First let see phone numbers all together (matrix)
		System.out.println("List of holder phones: "+phones);
		
		// Now apply flatMap to summarize it in a single list
		List<String> phonesWithFlatMap = Factory.getHolders().stream()
			.map(Holder::getPhoneNumbers)
			.flatMap(List::stream) // Stream<String> with the phones
			.collect(Collectors.toList());
		
		System.out.println("flatMap():\n\t List of flatten holder phones: "+phonesWithFlatMap);
		
	}

	private static void distinct_getDistinctHolderAges() {
		List<Integer> ages = Factory.getHolders().stream()
			.map(Holder::getAge)
			.collect(Collectors.toList());
		
		System.out.println("All ages: "+ages);
		
		List<Integer> distinctAges = ages.stream()
			.distinct()
			.collect(Collectors.toList());

		System.out.println("distinct():\n\t Unique ages: "+distinctAges);
		
	}
	
	private static void sortedDefault_getSorteredAccountBalances() {
		List<Long> balancesAsc = Factory.getAccountsWithHolders().stream()
				.map(Account::getBalance)
				.sorted() // ascending
				.collect(Collectors.toList());
		
		System.out.println("sorted():\n\t Sorted balances: "+balancesAsc);
		
	}
	
	private static void sortedWithComparator_getSorteredAccountsByBalance() {
		List<Account> accounts = Factory.getAccountsWithHolders().stream()
				.sorted(Comparator.comparing(Account::getBalance).reversed()) // descending
				.collect(Collectors.toList());
		
		System.out.println("sorted(Comparator):\n\t Sorted accounts by balance: "+accounts);
		
	}
	
	private static void filter_getFilteredAccounts() {
		List<Account> sharedAccounts = Factory.getAccountsWithHolders().stream()
				.filter(Account::getIsShared)
				.collect(Collectors.toList());
		
		System.out.println("filter():\n\t Filtered shared accounts: "+sharedAccounts);
		
		List<Account> dFilteredAccounts = Factory.getAccountsWithHolders().stream()
				.filter(a -> a.getCurrency().equals("EUR"))
				.filter(a -> a.getBalance().compareTo(40000L)>0)
				.collect(Collectors.toList());
		
		System.out.println("filter():\n\t Double-filtered accounts: "+dFilteredAccounts);

	}
	
	private static void reduce_getTotalBalance() {
		Optional<Long> total = Factory.getAccountsWithHolders().stream()
			.map(Account::getBalance)
			.reduce((a, b)->a+b);
		if(total.isPresent())
			System.out.println("reduce():\n\t Total balance of the accounts: "+total.get());
		else
			System.out.println("reduce():\n\t No present value");
	}
	
	private static void mapFilterAndReducePattern_getTotalBalanceOfEURAccounts() {
		Optional<Long> eurTotal = Factory.getAccountsWithHolders().stream()
				.filter(a -> a.getCurrency().equals("EUR"))
				.map(Account::getBalance)
				.reduce((a, b)->a+b);
		if(eurTotal.isPresent())
			System.out.println("map+filter+reduce:\n\t Total balance of the EUR accounts: "+eurTotal.get());
		else
			System.out.println("map+filter+reduce:\n\t No present value");
	}
	
	private static void limitAndSkip_getSpecificPhones() {
		// The list of all registered phones 
		List<String> phones = Factory.getHolders().stream()
			.map(Holder::getPhoneNumbers)
			.flatMap(List::stream) // Stream<String> with the phones
			.collect(Collectors.toList());
		
		System.out.println("All phones "+phones);
		
		List<String> limitedPhones = phones.stream()
			.limit(3)
			.collect(Collectors.toList());
		
		System.out.println("limit():\n\t Limit 3 first phones "+limitedPhones);
		
		List<String> skippedPhones = phones.stream()
				.skip(4)
				.collect(Collectors.toList());
		
		System.out.println("skip():\n\t Skip 4 phones, so 2 last phones "+skippedPhones);
		
		List<String> range = phones.stream()
				.skip(1)
				.limit(4)
				.collect(Collectors.toList());
		
		System.out.println("limit() & skip():\n\t Skip 1 phone and limit 4 next, so 4 middle phones "+range);

	}
	
}
