package com.respeso.summary;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.respeso.data.Account;
import com.respeso.data.factory.Factory;

/**
 * Examples for Parallel Stream uses.</br>
 * </br>
 * A parallel stream splits the source of data in to multiple parts, process them parallelly and
 * combine the result.</br>
 * Parallel Stream uses the Fork/Join framework that got introduced in Java 7.</br>
 * Number of threads created == number of processors available in the machine.</br>
 * </br>
 * Is always recommended to check the performance before to choose using parallel stream, 
 * e.g. parallel stream always do the unboxing operation to a list of Integer and penalize the performance 
 * therefore in this case is better to use sequential streams.</br>
 * </br>
 * If parallel streams operates with mutable variables, the result is incorrect. Avoid using parallel streams in this case
 * 
 * @author Rodrigo Espeso
 *
 */
public class ParallelStream {

	public static void main(String[] args) {
		System.out.println("Available processors: "+Runtime.getRuntime().availableProcessors());

		sequentialStream_summingTime();
		parallelStream_summingTime();
		sequentialStream_multipleOperationsTime();
		parallelStream_multipleOperationsTime();

	}

	private static void sequentialStream_summingTime() {
		long duration;
		long start;
		long end;

		start = System.currentTimeMillis();
		int seqSum = IntStream.rangeClosed(1, 100000).sum(); // SEQUENTIAL
		end = System.currentTimeMillis();
		duration = end-start;
		
		System.out.println("sum() from 1 to 100.000 sequentially " + seqSum + " in "+duration+"ms");
	}

	/**
	 * Method "parallel" is needed to be used in order to achieve the parallel
	 * computing of streams
	 */
	private static void parallelStream_summingTime() {
		long duration;
		long start;
		long end;
		start = System.currentTimeMillis();
		int parSum = IntStream.rangeClosed(1, 100000).parallel().sum(); // PARALLEL
		end = System.currentTimeMillis();
		duration = end-start;

		System.out.println("sum() from 1 to 100.000 parallelly " + parSum + " in "+duration+"ms");
	}

	private static void sequentialStream_multipleOperationsTime() {
		long duration;
		long start;
		long end;
		
		start = System.currentTimeMillis();
		Factory.getAccountsWithHolders().stream().map(Account::getHolders)
				.flatMap(List::stream).distinct().sorted().collect(Collectors.toList());
		end = System.currentTimeMillis();
		duration = end-start;
	
		System.out.println("Several mapping operations sequentially in "+duration+"ms");
		
	}

	private static void parallelStream_multipleOperationsTime() {
		long duration;
		long start;
		long end;
		
		start = System.currentTimeMillis();
		Factory.getAccountsWithHolders().stream().parallel().map(Account::getHolders)
				.flatMap(List::stream).distinct().sorted().collect(Collectors.toList());
		end = System.currentTimeMillis();
		duration = end-start;
	
		System.out.println("Several mapping operations parallelly in "+duration+"ms");
		
	}
}
