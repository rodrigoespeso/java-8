package com.respeso.summary;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.respeso.data.Account;
import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

/*
 * Java 8 Terminal operations.
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
 * 		This collector is equivalent to the groupBy() operation in SQL.
 * 		Used to group the elements based on a property.
 * 		The output of the groupingBy() is going to be a Map<K,V>
 * 		There are three different versions of groupingBy().
 * partitioningBy() 
 * 		This collector is also a kind of groupingBy().
 * 		Accepts a predicate as an input. 
 * 		Return type of the collector is going to be Map<K,V>
 * 		The key of the return type is going to be a Boolean.
 * 		There are two different versions of partitioningBy()
 * 
 */
public class StreamsTerminalOperations {

	public static void main(String[] args) {
		/*
		 * joining() Collector that performs the String concatenation on the
		 * elements in the stream. This method has three different overloaded
		 * versions.
		 */
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
		
		/*
		 * counting() Collector that returns the total number of elements as a
		 * result.
		 */
		long count = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.counting());
		System.out.println("count(): The total of accounts is " + count);
		System.out.println();
		
		/*
		 * mapping() This collector applies a transformation function first and
		 * then collects the data in a collection( could be any type of
		 * collection)
		 */
		Set<String> namesSet = Factory.getHolders().stream()
				// this avoids the additional map intermediate operation.
				.collect(mapping(Holder::getName, toSet()));

		System.out.println("mapping(): Names set: " + namesSet);

		List<String> namesList = Factory.getHolders().stream()
				// this avoids the additional map intermediate operation.
				.collect(mapping(Holder::getName, toList()));

		System.out.println("mapping(): Names list: " + namesList);
		System.out.println();
		/*
		 * maxBy()
		 * 		This collector is used in conjunction with comparator, as an input param. 
		 * 		Returns the max element based on the property passed to the comparator. 
		 */
		Optional<Account> maxAccount = Factory.getAccountsWithHolders().stream()
				.collect(Collectors.maxBy(Comparator.comparing(Account::getBalance)));
		if(maxAccount.isPresent())
			System.out.println("minBy(): Min balance account: " + maxAccount.get());

		/* 
		 * minBy()
		 * 		This collector is used in conjunction with comparator, as an input param
		 * 		Returns the	smallest element based on the property passed to 
		 * 		the comparator.
		 * 
		 */
		Optional<Account> minAccount = Factory.getAccountsWithHolders().stream()
                .collect(Collectors.minBy(Comparator.comparing(Account::getBalance)));
		if(minAccount.isPresent())
		System.out.println("minBy(): Min balance account: " + minAccount.get());
		
		/*
		 * summingInt(), summingLong(), summingDouble()
		 * 		This collector returns the sum as a result.
		 */
		Integer sumInt = IntStream.rangeClosed(1, 10).boxed()
			.collect(Collectors.summingInt(Integer::intValue));
		Long sumLong = LongStream.rangeClosed(1, 10).boxed()
				.collect(Collectors.summingLong(Long::longValue));
		Double sumDouble = IntStream.rangeClosed(1, 10).asDoubleStream().boxed()
				.collect(Collectors.summingDouble(Double::doubleValue));
		System.out.println("summingInt(): "+sumInt);
		System.out.println("summingLong(): "+sumLong);
		System.out.println("summingDouble(): "+sumDouble);
		
		/*
		 * averagingInt(), averagingLong, averagingDouble()
		 * 		This collector returns the average as a result.
		 */
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

}
