package com.respeso.summary;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Java 8 Streams. Exercises:</br>
 * 
 * of(), iterate() and generate()
 * 
 * @author Rodrigo Espeso
 * 
 */
public class StreamsFactoryMethods {
	public static void main (String[] args) {
		of();
		iterate();
		generate();
	}

	/*
	 * Generates a Streams with the given values
	 */
	private static void of() {
		System.out.println("of():");
		Stream<String> stringStream = Stream.of("Adam", "Dan", "Jenny", "Dave");
		stringStream.forEach(s -> System.out.println("\t"+s));

	}
	
	/*
	 * Generates an infinite Streams. Starting with a seed and applying a operation
	 * to each generated value of the streams to calculate the next one.
	 */
	private static void iterate() {
        List<Integer> integerList  = Stream.iterate(1, x->x++)
                .limit(10)
                .map(Integer::new)
                .collect(toList());
        
		System.out.println("iterate():\n\t"+integerList);
		
	}
	
	/*
	 * Execute a supplier infinite times saving the result in a stream.
	 */
	private static void generate() {

        Supplier<Integer> supplier = new Random()::nextInt;

        List<Integer> randomList  = Stream.generate(supplier)
                .limit(10)
                .collect(toList());
        System.out.println("generate() : " + randomList);
	}
}
