package com.respeso.summary;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.learnJava.data.Student;

public class MethodReferenceExamples {
	
	/*
	 * Method References.
	 * Used to refactor lambdas.
	 */
	
	// Class::instance-method
	static Consumer<String> printConsumer = System.out::println; // s -> System.out.println(s)
	static Function<String,String> toUpperCase = String::toUpperCase; // s -> s.toUpperCase()
	// Instance::instance-method
	static Consumer<Student> c3 = (Student::printListOfActivities); // student -> student.printListOfActivities()
	
	
	/*
	 * Constructor Reference
	 */
	Supplier<Date> today = Date::new; // () -> return new Date()
	
	/*
	 * Refactor
	 */
	Predicate<String> isNullOrEmpty = MethodReferenceExamples::isNullOrEmpty; // s -> isNullOrEmtpy(s)
	
	public static void main (String[] args) {
		printConsumer.accept("example");
		
		/*
		 * Effectively final. Advantages:
		 * Before Java8 any varia that's used inside the anonymous class should be declared final
		 * - Easy to perform concurrency operations.
		 * - Promotes Functional Programming and demotes Imperative Programming
		 */
        int value = 4; //effectively final
        Consumer<Integer> c1 = (a) -> {
//            value=6;	// effectively final error
            //  System.out.println(i+value);
        };
		
	}
	
	public static boolean isNullOrEmpty(String s) {
		return  s == null || s.isEmpty();
	}
}
