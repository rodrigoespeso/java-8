package com.respeso.summary;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.respeso.data.Account;
import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

/*
 * Java 8 Terminal operations. Exercises:
 * 
 * Terminal Operations collects the data for you.
 * Terminal Operations starts the whole stream pipeline.
 * 
 * collect()
 * 		The collect() method takes in an input of type Collector.
 *		Produces the result as per the input passed to the collect() method.
 * joining()
 * counting() 
 * mapping() 
 * maxBy() with comparator()
 * minBy() with comparator()
 * summingInt(), summingLong(), summingDouble()
 * averagingInt(), averagingLong(), averagingDouble()
 * groupingBy() 
 * partitioningBy() 
 * 
 */
public class StreamsTerminalOperations {

	public static void main(String[] args) {
		/*
		 * joining() Collector that performs the String concatenation on the
		 * elements in the stream. This method has three different overloaded
		 * versions.
		 */
		joining_formatingANameList();
		
		/*
		 * counting() Collector that returns the total number of elements as a
		 * result.
		 */
		count_countingTheTotalOfHolders();
		
		/*
		 * mapping() This collector applies a transformation function first and
		 * then collects the data in a collection( could be any type of
		 * collection)
		 */
		mapping_getHolderListToSetAndToList();
		
		/*
		 * maxBy()
		 * 		This collector is used in conjunction with comparator, as an input param. 
		 * 		Returns the max element based on the property passed to the comparator. 
		 */
		maxBy_getTheMaxBalanceAccount();

		/* 
		 * minBy()
		 * 		This collector is used in conjunction with comparator, as an input param
		 * 		Returns the	smallest element based on the property passed to 
		 * 		the comparator.
		 * 
		 */
		minBy_getTheMinBalanceAccount();
		
		/*
		 * summingInt(), summingLong(), summingDouble()
		 * 		This collector returns the sum as a result.
		 */
		summing_summatoryOfIntegerLongAndDoubleOfARange();
		
		/*
		 * averagingInt(), averagingLong, averagingDouble()
		 * 		This collector returns the average as a result.
		 */
		avg_averageOfInteterLongAndDoubleOfARange();
		
		/*
		 * groupingBy() 
		 * 		This collector is equivalent to the groupBy() operation in SQL.
		 * 		Used to group the elements based on a property.
		 * 		The output of the groupingBy() is going to be a Map<K,V>
		 * 		There are three different versions of groupingBy().
		 * 		'classifier' is the key
		 * 		'downstream' (collector) is the value
		 * 		'supplier' defines the type of the value
		 * 
		 * groupingBy(classifier)
		 */
		groupingBy_genderUsingClassifier();

		
		/*
		 * groupingBy(classifier, downstream) - 2 collectors
		 */
		groupingBy_usingClassifierAndDownstrean_bySharedAndHealthyAccount();
		
		
		/*
		 * groupingBy(classifier, supplier, downstream)
		 * 1 param is the key, 2 the type of output, 3 outputvalue
		 */
		groupingBy_ClassifierSupplierAndDownstream_byName();
		
		/*
		 * groupingBy() with maxBy() and minBy() - two arg version
		 */
		 // Get the max account of all healthy and not healthy accounts
		groupingByMaxMinBy_maxAccountHealthyOrNot();
		
		/*
		 * partitioningBy() 
		 * 		This collector is also a kind of groupingBy().
		 * 		Accepts a predicate as an input. 
		 * 		Return type of the collector is going to be Map<K,V>
		 * 		The key of the return type is going to be a Boolean.
		 * 		There are two different versions of partitioningBy()
		 * 		partitioningBy(predicate), partitioningBy(predicate,downstream) // downstream -> could be of any
		 *		collector
		 */
		partitioningBy_euroAccounts();

	}

	private static void joining_formatingANameList() {
		String joining = Factory.getHolders().stream().map(Holder::getName)
				.collect(Collectors.joining());
		System.out.println("joining() simple: " + joining);

		String joining2 = Factory.getHolders().stream().map(Holder::getName)
				.collect(Collectors.joining(", "));
		System.out.println("joining() with delimiters: " + joining2);

		String joining3 = Factory.getHolders().stream().map(Holder::getName)
				.collect(Collectors.joining(", ", "{", "}"));
		System.out.println("joining() complete: " + joining3);
		System.out.println();
	}

	private static void count_countingTheTotalOfHolders() {
		long count = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.counting());
		System.out.println("count(): The total of accounts is " + count);
		System.out.println();
	}

	private static void mapping_getHolderListToSetAndToList() {
		Set<String> namesSet = Factory.getHolders().stream()
				// this avoids the additional map intermediate operation.
				.collect(mapping(Holder::getName, toSet()));
	
		System.out.println("mapping(): Names set: " + namesSet);
	
		List<String> namesList = Factory.getHolders().stream()
				// this avoids the additional map intermediate operation.
				.collect(mapping(Holder::getName, toList()));
	
		System.out.println("mapping(): Names list: " + namesList);
		System.out.println();
	}

	private static void maxBy_getTheMaxBalanceAccount() {
		Optional<Account> maxAccount = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.maxBy(Comparator.comparing(Account::getBalance)));
		if(maxAccount.isPresent())
			System.out.println("minBy(): Max balance account: " + maxAccount.get());
	}

	private static void minBy_getTheMinBalanceAccount() {
		Optional<Account> minAccount = Factory.getAccountsWithHolders().stream()
	            .collect(Collectors.minBy(Comparator.comparing(Account::getBalance)));
		if(minAccount.isPresent())
		System.out.println("minBy(): Min balance account: " + minAccount.get());
	}

	private static void summing_summatoryOfIntegerLongAndDoubleOfARange() {
		Integer sumInt = IntStream.rangeClosed(1, 10).boxed()
			.collect(Collectors.summingInt(Integer::intValue));
		Long sumLong = LongStream.rangeClosed(1, 10).boxed()
				.collect(Collectors.summingLong(Long::longValue));
		Double sumDouble = IntStream.rangeClosed(1, 10).asDoubleStream().boxed()
				.collect(Collectors.summingDouble(Double::doubleValue));
		System.out.println("summingInt(): "+sumInt);
		System.out.println("summingLong(): "+sumLong);
		System.out.println("summingDouble(): "+sumDouble);
	}

	private static void avg_averageOfInteterLongAndDoubleOfARange() {
		Double avgInt = IntStream.rangeClosed(1, 10).boxed()
				.collect(Collectors.averagingInt(Integer::intValue));
		Double avgLong = LongStream.rangeClosed(1, 10).boxed()
				.collect(Collectors.averagingLong(Long::longValue));
		Double avgDouble = IntStream.rangeClosed(1, 10).asDoubleStream().boxed()
				.collect(Collectors.averagingDouble(Double::doubleValue));
		System.out.println("averagingInt(): "+avgInt);
		System.out.println("averagingLong(): "+avgLong);
		System.out.println("averagingDouble(): "+avgDouble);
	}

	private static void groupingBy_genderUsingClassifier() {
		Map<String, List<Holder>> genderGroup = Factory.getHolders().stream()
				.collect(Collectors.groupingBy(Holder::getGender));
		System.out.println("groupingBy(classifier): Gender group:\n\t"+genderGroup);
	
		Map<String, List<Holder>> customGroup = Factory.getHolders().stream()
				.collect(Collectors.groupingBy(h -> h.getAge()<= 35 ? "YOUNG CLIENT" : "NORMAL CLIENT"));
		System.out.println("groupingBy(classifier): Age group:\n\t"+customGroup);
	}

	private static void groupingBy_usingClassifierAndDownstrean_bySharedAndHealthyAccount() {
		Map<Boolean, Map<Object, List<Account>>> twoLevelGroup = Factory.getAccountsWithHolders().stream()
		.collect(Collectors.groupingBy(Account::getIsShared, Collectors.groupingBy(a -> a.getBalance()>=30000 ? "HEALTHY" : "NOT")));
		System.out.println("groupingBy(classifier, downstream): Two level group, shared and healthy:\n\t"+twoLevelGroup);
	
		Map<Boolean, Double> sumOfSTypesOfAccounts = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.groupingBy(Account::getIsShared, Collectors.summingDouble(Account::getBalance)));
		System.out.println("groupingBy(classifier, downstream): Two level group 2:\n\t"+sumOfSTypesOfAccounts);
	}

	private static void groupingBy_ClassifierSupplierAndDownstream_byName() {
		LinkedHashMap<String, Set<Holder>> threeParamGroup = Factory.getHolders().stream()
				.collect(Collectors.groupingBy(Holder::getName, LinkedHashMap::new, toSet()));
		System.out.println("groupingBy(classifier, supplier, downstream): \n\t"+threeParamGroup);
	}

	private static void groupingByMaxMinBy_maxAccountHealthyOrNot() {
		Map<String, Optional<Account>> maxOfHealthyAndNot = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.groupingBy(a -> a.getBalance() >= 30000 ? "HEALTHY" : "NOT",
						Collectors.maxBy(Comparator.comparing(Account::getBalance)))); 
		System.out.println("groupingBy(classifier, downstream): max account of all healthy and not healthy accounts:\n\t"+maxOfHealthyAndNot);
		
		// we can avoid the Optional
		Map<String, Account> maxOfHealthyAndNot2 = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.groupingBy(a -> a.getBalance() >= 30000 ? "HEALTHY" : "NOT",
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Account::getBalance)), Optional::get))); 
		System.out.println("groupingBy(classifier, downstream): max account of all healthy and not healthy accounts no optional):\n\t"+maxOfHealthyAndNot2);
	}

	private static void partitioningBy_euroAccounts() {
		Predicate<Account> predicate = FunctionalInterfaces.isEuroAccount;
		Map<Boolean, List<Account>> euroGroupAccounts = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.partitioningBy(predicate));
		System.out.println("partitioningBy(predicate): 'is Euro account?' group: \n\t"+ euroGroupAccounts);
	
		Map<Boolean, Set<Account>> euroGroupAccountsSet = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.partitioningBy(predicate, Collectors.toSet()));
		System.out.println("partitioningBy(predicate,downstream): 'is Euro account?' group (set): \n\t"+ euroGroupAccountsSet);
	}

}
