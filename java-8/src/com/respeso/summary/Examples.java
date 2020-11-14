package com.respeso.summary;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Examples{
	
	/*
	 * Consumer<T>
	 * 
	 * Accepts a param and operate with it, but do not return nothing: implementation
	 * of a void method.
	 * 
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
	static BiPredicate<String, String> areEquals = (s1, s2) -> {return s1.equals(s2);};
	static BiPredicate<String, String> leftSmallerThanRight = (s1, s2) -> {return s1.length()<s2.length();};
	
	/*
	 * Function<T>
	 */
	static Function<Integer, Integer> increaseOne = i -> {return ++i;}; 
	static Function<Integer, Integer> duplicate = i -> {return i*2;}; 
	/*
	 * UnaryOperation<T> and BinaryOperation<T, U>>
	 */
	
	/*
	 * Supplier<T>
	 */
	
    public static void main(String[] args) {

    	/*
		 * Consumer<T> methods. 
		 * - accept(T param) : the input param
		 * - andThen(Consumer<? super T> cons) to perform chaining
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
    	
    	/*
    	 * BiPredicate<T, U> methods.
    	 * (same as Predicate<T>)
    	 */
    	String s1 = "Example";
    	String s2 = "Longest example";
    	result = areEquals.test(s1, s2);
    	System.out.println("Example for BiPredicate: "+result); // expected false
    	
    	result = areEquals.or(leftSmallerThanRight).test(s1, s2); // expected true;
    	System.out.println("Example for BiPredicate using OR chaining: "+result); // expected true

    	
		/*
		 * Function<T, U> methods.
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
}
