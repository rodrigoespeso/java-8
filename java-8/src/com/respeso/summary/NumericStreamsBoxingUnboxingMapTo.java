package com.respeso.summary;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/*
 * Java 8 Numeric Streams. Use examples of:
 * 
 * boxing:
 * 		Converting a primitive type to Wrapper Class type
 * unboxing:
 * 		Converting a Wrapper Class type to primitive type.
 * 
 * mapToObj():
 * 		Convert a each element numeric stream to some Object.
 * mapToLong():
 * 		Convert a numeric stream to a Long Stream.
 * mapToDouble():
 * 		Convert a numeric stream to a Double Stream.
 */
public class NumericStreamsBoxingUnboxingMapTo {
	
	public static void main (String [] args) {
		/*
		 * Boxing
		 */
		List<Integer> integers = IntStream.rangeClosed(1, 5) // IntStream of 10 elements
        .boxed() //Stream<Integer>
        //all the elements will be passed one by one to get to the result as the collection.
        .collect(Collectors.toList());
		System.out.println("Boxing Integer list (boxed()): "+ integers);
	
		/*
		 * Unboxing. Wrapper to primitive.
		 */				
		IntStream intS = integers.stream()
				// Wrapper Integer Values (Integer)
				// to int
				.mapToInt(Integer::intValue);
		System.out.println("\nUnboxing:");
		intS.forEach(i -> System.out.print(i+", "));
		System.out.println();
		/*
		 * mapToLong(), mapToDouble()
		 */
		LongStream longS = IntStream.rangeClosed(1, 5).mapToLong((i) -> i);
		System.out.println("\nmapToLong():");
		longS.forEach(l -> System.out.print(l+", "));
		System.out.println();
		
		DoubleStream doubleS = IntStream.rangeClosed(1, 5).mapToDouble((i) -> i);
		System.out.println("\nmapToDouble():");
		doubleS.forEach(d -> System.out.print(d+", "));
		
	}
	
}
