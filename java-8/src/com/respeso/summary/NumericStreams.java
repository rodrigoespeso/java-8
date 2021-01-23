package com.respeso.summary;

import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Java 8 Numeric Streams. Exercises:
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
 * @author Rodrigo Espeso
 */
public class NumericStreams {
	public static void main(String[] args) {
		
		/*
		 * With range() the end limit is not included in the Stream.
		 * With rangeClosed() the end limit is included in the Stream.
		 * The same can be done with LongStream.
		 */
		range_printOpenRangeOfNumbers();
		rangeClosed_printClosedRangeOfNumbers();
		/*
		 * DoubleStream does not support range methods, we can get it through IntStrean
		 */
		rangeClosedOnDouble_trick();
		
		/*
		 * max(), min(), sum() return OptionalLong or OptionalInt (in each case)
		 * average() returns OptionalDouble.
		 */
		max_getTheMaximunValueOfARange();
		min_getTheMinimunValueOfARange();
		sum_getTheSummatoryOfARange();
		avg_getTheAverageNumberOfARange();
		
	}

	private static void range_printOpenRangeOfNumbers() {

		IntStream intStream = IntStream.range(1, 20);
		
		System.out.println("range():");
		intStream.forEach(i -> System.out.print(i+", "));
		System.out.print("\ncount(): "+IntStream.range(1, 20).count());
	}

	private static void rangeClosed_printClosedRangeOfNumbers() {
		IntStream intStreamClosed = IntStream.rangeClosed(1, 20);
		
		System.out.println("\nrangeClosed():");
		intStreamClosed.forEach(i -> System.out.print(i+", "));
		System.out.print("\ncount(): "+IntStream.rangeClosed(1, 20).count());
	}

	private static void rangeClosedOnDouble_trick() {
		
		DoubleStream doubleStream = IntStream.rangeClosed(1, 20).asDoubleStream();
		
		System.out.println("\nDouble virtual 'rangeClosed':");
		doubleStream.forEach(i -> System.out.print(i+", "));
	}

	private static void max_getTheMaximunValueOfARange() {
		LongStream forMax = LongStream.rangeClosed(-5, 15);
		OptionalLong max = forMax.max();
		if(max.isPresent())
			System.out.print("\nmax() of [-5, 15]: "+ max.getAsLong());
	}

	private static void min_getTheMinimunValueOfARange() {
		LongStream forMin = LongStream.rangeClosed(-5, 15);
		OptionalLong min = forMin.min();
		if(min.isPresent())
			System.out.print("\nmin() of [-5, 15]: "+ min.getAsLong());
	}

	private static void sum_getTheSummatoryOfARange() {
		LongStream forSum = LongStream.rangeClosed(-5, 15);
		OptionalLong sum = forSum.max();
		if(sum.isPresent())
			System.out.print("\nsum() of [-5, 15]: "+ sum.getAsLong());
	}

	private static void avg_getTheAverageNumberOfARange() {
		LongStream forAvg = LongStream.rangeClosed(-5, 15);
		OptionalDouble avg = forAvg.average();
		if(avg.isPresent())
			System.out.print("\naverage() of [-5, 15]: "+ avg.getAsDouble());
	}
}
