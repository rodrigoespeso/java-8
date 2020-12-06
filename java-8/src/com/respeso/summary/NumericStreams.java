package com.respeso.summary;

import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/*
 * Java 8 Numeric Streams. Use examples of:
 * 
 * range()
 * rangeClosed()
 * count()
 * foreach()
 * sum()
 * max()
 * min()
 * average()
 * 
 */
public class NumericStreams {
	public static void main(String[] args) {
		/*
		 * With range() the end limit is not included in the Stream
		 */
		IntStream intStream = IntStream.range(1, 20);
		
		System.out.println("range():");
		intStream.forEach(i -> System.out.print(i+", "));
		System.out.println("\ncount(): "+IntStream.range(1, 20).count());
		
		/*
		 * With rangeClosed() the end limit is included in the Stream
		 */
		IntStream intStreamClosed = IntStream.rangeClosed(1, 20);
		
		System.out.println("\nrangeClosed():");
		intStreamClosed.forEach(i -> System.out.print(i+", "));
		System.out.println("\ncount(): "+IntStream.rangeClosed(1, 20).count());
		
		/*
		 * The same can be done with LongStream.
		 */ 
		
		/*
		 * DoubleStream does not support range methods, we can get it through IntStrean
		 */
		DoubleStream doubleStream = IntStream.rangeClosed(1, 20).asDoubleStream();
		
		System.out.println("\nDouble virtual 'rangeClosed':");
		doubleStream.forEach(i -> System.out.print(i+", "));
		System.out.println();
		
		/*
		 * max(), min(), sum() return OptionalLong or OptionalInt (in each case)
		 * average() returns OptionalDouble.
		 */
		LongStream forMax = LongStream.rangeClosed(-5, 15);
		OptionalLong max = forMax.max();
		if(max.isPresent())
			System.out.print("\nmax() of [-5, 15]: "+ max.getAsLong());
		
		LongStream forMin = LongStream.rangeClosed(-5, 15);
		OptionalLong min = forMin.min();
		if(max.isPresent())
			System.out.print("\nmin() of [-5, 15]: "+ min.getAsLong());

		LongStream forSum = LongStream.rangeClosed(-5, 15);
		OptionalLong sum = forSum.max();
		if(max.isPresent())
			System.out.print("\nsum() of [-5, 15]: "+ sum.getAsLong());
		
		LongStream forAvg = LongStream.rangeClosed(-5, 15);
		OptionalDouble avg = forAvg.average();
		if(max.isPresent())
			System.out.print("\naverage() of [-5, 15]: "+ avg.getAsDouble());
		
	}
}
