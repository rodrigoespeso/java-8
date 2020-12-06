package com.respeso.summary;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
 * Comparator as an input parameter and Optional as an output.
 * maxBy() 
 * 		This collector is used in conjunction with comparator. 
 * 		Returns the max element based on the property passed to the comparator.
 * minBy()
 *		This collector is used in conjunction with comparator. 
 *		Returns the smallest element based on the property passed to the comparator.
 * summingInt()
 * 		This collector returns the sum as a result.
 * averagingInt()
 * 		This collector returns the average as a result.
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
 * There are two different versions of partitioningBy()
 * 
 */
public class StreamsTerminalOperations {
	
	public static void main (String [] args) {
		/*
		 * joining() 
		 * Collector that performs the String concatenation on the elements in the
		 * stream. This method has three different overloaded versions.
		 */
		String joining = Factory.getHolders().stream()
			.map(Holder::getName)
			.collect(Collectors.joining());
		System.out.println("joining: "+joining);
		
		String joining2 = Factory.getHolders().stream()
				.map(Holder::getName)
				.collect(Collectors.joining(", "));
		System.out.println("joining with delimiters: "+joining2);
		
		String joining3 = Factory.getHolders().stream()
				.map(Holder::getName)
				.collect(Collectors.joining(", ", "{", "}"));
		System.out.println("joining complete: "+joining3);
		
		/*
		 * counting() 
		 * 	Collector that returns the total number of elements as a result.
		 */
		long count = Factory.getAccountsWithHolders().stream()
			.collect(Collectors.counting());
		System.out.println("count: The total of accounts is "+count);
		
		/*
		 * mapping() 
		 * 		This collector applies a transformation function first and then 
		 * 		collects the data in a collection( could be any type of collection)
		 */
	       Set<String> namesSet = Factory.getHolders()
	                .stream()
	                .collect(mapping(Holder::getName, toSet())); // this avoids the additional map intermediate operation.

	        System.out.println("mapping: Names set: " + namesSet);

	        List<String> namesList = Factory.getHolders()
	                .stream()
	                .collect(mapping(Holder::getName, toList())); // this avoids the additional map intermediate operation.

	        System.out.println("mapping: Names list: " + namesList);
	}
	
}
