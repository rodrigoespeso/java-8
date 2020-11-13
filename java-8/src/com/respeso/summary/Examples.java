package com.respeso.summary;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Examples{
	
	/*
	 * Consumer<T>
	 * 
	 * Accepts a param and operate with it, but do not return nothing: implementation
	 * of a void method.
	 */
	static Consumer<String> consumerLambda = (s) -> {System.out.println(s.concat(" with Lambda"));}; 

	/*
	 * BiConsumer <T, U>
	 * 
	 * Accepts 2 params and operate with them, but do not return nothing: implementation
	 * of a void method.
	 */
	static BiConsumer<String, Integer> biConsumer = (s, i) -> {System.out.println(s.concat(" "+i.toString()));}; 
	static BiConsumer<String, Integer> biConsumerPost = (s, i) -> {
		i++;
		System.out.println(s.concat(" consumed "+i));
	};
	
	
	/*
	 * Predicate<T>
	 * 
	 * Accepts a param an evaluate an expresion using it. Implementation of a
	 * boolean return method.
	 */
	static Predicate<Integer> isEven = (i)->{return i%2 == 0;};
	static Predicate<Integer> isBiggerThanTen = i-> i>10; // note how the lambda can be reduced
	
	/*
	 * BiPredicate<T, U>
	 * 
	 * Accepts 2 params an evaluate an expresion using them. Implementation of a
	 * boolean return method.
	 */

	/*
	 * Function<T>
	 */
	
	/*
	 * UnaryOperation<T> and BinaryOperation<T, U>>
	 */
	
	/*
	 * Suplier<T>
	 */
	
    public static void main(String[] args) {

    	/*
		 * Consumer<T> methods: 
		 * - 'accept' the input param
		 * - 'andThen' to perform chaining
		 */
    	String str = "example";
    	System.out.print("Example for Consumer with lambda: ");
    	consumerLambda.accept(str);
    	
    	System.out.print("Example for Consumer using chaining: ");
    	consumerLambda.andThen(consumerLambda).accept("with other "+str);
    	
    	/*
		 * BiConsumer<T, U> methods: 
		 * (same as Consumer<T>)
		 */
    	System.out.print("Example for BiConsumer: ");
    	biConsumer.accept(str, 4);
    	
    	System.out.print("Example for BiConsumer using chaining: ");
    	biConsumer.andThen(biConsumerPost).accept("with other "+str, 4);
    	
    	/*
    	 * Predicate<T> methods:
    	 * - 'test' to input params
    	 * - 'and' AND operation with other Predicate, performing chaining
    	 * - 'or' OR operation with other Predicate, performing chaining
    	 */
    	Integer num = 8;
    	boolean result = isEven.test(num);
    	System.out.println("Example for Predicate: "+result); // expected true

    	result = isEven.and(isBiggerThanTen).test(num);
    	System.out.println("Example for Predicate using AND chaining: "+result); // expected false
    	
    	result = isEven.or(isBiggerThanTen).test(num);
    	System.out.println("Example for Predicate using OR chaining: "+result); // expected true
    }
}
