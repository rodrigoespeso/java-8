package com.respeso.summary;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionalInterfacesExamples{
	
	/*
	 * Consumer<T>
	 * 
	 * Accepts a param and operate with it, but do not return nothing: implementation
	 * of a void method.
	 * 
	 */
	static Consumer<String> consumerLambda = (s) -> {System.out.println(s.concat(" with Lambda"));}; 
	static Consumer<String> consumerPost = (s) -> {System.out.println(s.concat(" with Lambda again"));}; 

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
	 * Accepts a param an evaluate an expression using it. Implementation of a
	 * boolean return method.
	 */
	static Predicate<Integer> isEven = (i)->{return i%2 == 0;};
	static Predicate<Integer> isBiggerThanTen = i-> i>10; // note how the lambda can be reduced
	
	/*
	 * BiPredicate<T, U>
	 * 
	 * Accepts 2 params an evaluate an expression using them. Implementation of a
	 * boolean return method.
	 */
	static BiPredicate<String, String> areEquals = (s1, s2) -> {return s1.equals(s2);};
	static BiPredicate<String, String> leftSmallerThanRight = (s1, s2) -> {return s1.length()<s2.length();};
	
	/*
	 * Function<T, U> 
	 * 
	 * Implements a function with one T type param and return a U
	 * type value
	 */
	static Function<Integer, Integer> increaseOne = i -> {return ++i;}; 
	static Function<Integer, Integer> duplicate = i -> {return i*2;}; 
	
	/*
	 * BiFunction<T, U, V> 
	 * 
	 * Implements a function with two params (types T and U) and
	 * return a V type value
	 */
	static BiFunction<Integer, Integer, Integer> add = (i1, i2) -> {return i1 + i2;}; 
	static BiFunction<Integer, Integer, Integer> multiply = (i1, i2) -> {return i1 * i2;}; 
	
	/*
	 * UnaryOperator<T> and BinaryOperation<T, U>>
	 */
	static UnaryOperator<Integer> increaseOneUO = i -> {return ++i;}; // the type is shared with the param and return
	static BinaryOperator<Integer> addBO = (i1, i2) -> {return i1 + i2;}; // the type is shared with the param and return
	
	/*
	 * Supplier<T>
	 * 
	 * Implements no param function with T type return
	 */
	static Supplier<String> exampleString = ()->{return "example";};
	
    public static void main(String[] args) {

    	usesOfConsumer();
    	
    	usesOfBiConsumer();
    	
		usesOfPredicate();
    	
    	usesOfBiPredicate();
    	
		usesOfFunction();

		usesOfBiFunction();

		usesOfUnaryOperatorAndBinaryOperator();
		
		usesOfSupplier();

    }
    
	private static void usesOfConsumer() {
		/*
		 * Consumer<T> methods. 
		 * - accept(T param) : the input param
		 * - andThen(Consumer<? super T> cons) to perform chaining
		 */
    	String str = "example";
    	System.out.print("Example for Consumer with lambda: ");
    	consumerLambda.accept(str);
    	
    	System.out.print("Example for Consumer using chaining: ");
    	consumerLambda.andThen(consumerPost).accept("with other "+str);
	}
	
	private static void usesOfBiConsumer() {
		/*
		 * BiConsumer<T, U> methods: 
		 * (same as Consumer<T>)
		 */
		String str = "example";
		System.out.print("Example for BiConsumer: ");
		biConsumer.accept(str, 4);
		
		System.out.print("Example for BiConsumer using chaining: ");
		biConsumer.andThen(biConsumerPost).accept("with other "+str, 4);
	}
	
	private static void usesOfPredicate() {
		/*
		 * Predicate<T> methods.
		 * - test(T param) : to input param
		 * - and(Predicate<? super T> pred) : AND operation with other Predicate, performing chaining
		 * - or(Perdicate<? super T> pred) : OR operation with other Predicate, performing chaining
		 */
		Integer num = 8;
		boolean result = isEven.test(num);
		System.out.println("Example for Predicate: "+result); // expected true
	
		result = isEven.and(isBiggerThanTen).test(num);
		System.out.println("Example for Predicate using AND chaining: "+result); // expected false
		
		result = isEven.or(isBiggerThanTen).test(num);
		System.out.println("Example for Predicate using OR chaining: "+result); // expected true
	}
	
	private static void usesOfBiPredicate() {
		/*
		 * BiPredicate<T, U> methods.
		 * (same as Predicate<T>)
		 */
		String s1 = "Example";
		String s2 = "Longest example";
		boolean result = areEquals.test(s1, s2);
		System.out.println("Example for BiPredicate: "+result); // expected false
		
		result = areEquals.or(leftSmallerThanRight).test(s1, s2); // expected true;
		System.out.println("Example for BiPredicate using OR chaining: "+result); // expected true
	}
	
	private static void usesOfFunction() {
		/*
		 * Function<T, U> methods.
		 * - apply(T param) : to receive the param
		 * - compose(Function <?, ?> f) : function to be executed after the first one
		 * - andThen(Function <?, ?> f) : function to be executed before the first one
		 */
		Integer i = 5;
		Integer sum = increaseOne.apply(i);
		System.out.println("Example for Function: "+sum); // expected 6
		
		// increaseOne is done AFTER duplicate
		sum = duplicate.andThen(increaseOne).apply(i);
		System.out.println("Example for Function using andThen chaining: "+sum); // expected 11
		
		// increaseOne is done BEFORE duplicate
		sum = duplicate.compose(increaseOne).apply(i);
		System.out.println("Example for Function using compose chaining: "+sum); // expected 12
	}
	
	private static void usesOfBiFunction() {
		/*
		 * BiFunction<T, U> methods. apply(T param) : to receive the param
		 * - andThen(Function <?> f) : function to be executed over the result of the
		 * - applied bifunciton
		 */
		Integer i1 = 5;
		Integer i2 = 7;
		Integer sum = add.apply(i1, i2);
		System.out.println("Example for BiFunction: "+sum); // expected 6
		
		// increaseOne is done AFTER add
		sum = add.andThen(increaseOne).apply(i1, i2);
		System.out.println("Example for Function using andThen chaining: "+sum); // expected 11
	}

	private static void usesOfUnaryOperatorAndBinaryOperator() {
		
		/*
		 * UnaryOperator<T, methods.
		 * (same as Function)
		 */
		Integer i = 5;
		Integer sum = increaseOneUO.apply(i);
		System.out.println("Example for UnaryOperator: "+sum); // expected 6
		
		// increaseOne is done AFTER duplicate
		sum = duplicate.andThen(increaseOneUO).apply(i); // Same uses
		System.out.println("Example for UnaryOperator using andThen chaining: "+sum); // expected 11
		
		// increaseOne is done BEFORE duplicate
		sum = duplicate.compose(increaseOneUO).apply(i);
		System.out.println("Example for UnaryOperator using compose chaining: "+sum); // expected 12
		
		/*
		 * BinaryOperator<T, methods.
		 * (same as BiFunction)
		 */
		
		Integer i1 = 5;
		Integer i2 = 7;
		sum = addBO.apply(i1, i2);
		System.out.println("Example for BinaryOperator: "+sum); // expected 6
		
		// increaseOne is done AFTER add
		sum = addBO.andThen(increaseOneUO).apply(i1, i2);
		System.out.println("Example for BinaryOperator using andThen chaining: "+sum); // expected 11
	}

	private static void usesOfSupplier() {
		/*
		 * Supplier<T> methods.
		 * - get() : Perform and return
		 */

		String str = exampleString.get();
		System.out.println("Example for Supplier: "+str); // expected "example"
	}
	
	
}
