package com.respeso.summary;

import java.util.Optional;

import com.learnJava.data.Bike;
import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;
import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

/**
 * Java 8 Optional library. Exercises.</br>
 * </br>
 * Introduced as part of Java 8 to represent a Non-Null value. Avoids Null Pointer Exception and Unnecessary Null Checks.
 * Inspired from the new languages such as scala , groovy etc.
 * 
 * Methods: get(), isPresent(), ifPresent(), orElse(), orElseGet(), orElseThrow(), filter(), map() and flatmap().

 * @author Rodrigo Espeso
 *
 */
public class OptionalLibrary {

	public static void main(String[] args) {
		
		Optional<Holder> holderOpt = Optional.ofNullable(Factory.holderSupplier.get());
		get_showNameOfOptionalHolder(holderOpt);
		
		Optional<Holder> nullHolderOpt = Optional.ofNullable(null);
		get_showNameOfOptionalHolder(nullHolderOpt);

		orElse_showNameOfOptionalHolder();
		orElseGet_showNameOfOptionalHolder();
		try {
		orElseThrow_showNameOfOptionalHolder();
		}catch(RuntimeException e) {
			System.out.println("\tException catched!: "+e.getMessage());
		}
		ifPresent_showNameOfOptionalHolder();
		filter_filteredHolderByName();
		map_showHolderNameAfterFilter();
		flatmap_showHolderNameAfterFilter();
	}

	private static void get_showNameOfOptionalHolder(Optional<Holder> holderOpt) {
		Optional<String> nameOpt = Optional.empty();
		if(holderOpt.isPresent()) {
			nameOpt = holderOpt.map(Holder::getName);
		}
		
		if(nameOpt.isPresent()) {
			System.out.println("get():\n\tThe name of the holder is: "+nameOpt.get());
		}else {
			System.out.println("get():\n\tName not present");
		}
	}

	private static void orElse_showNameOfOptionalHolder() {
		Optional<Holder> empty = Optional.ofNullable(null);
		String noName = empty.map(Holder::getName).orElse("Default");
		System.out.println("orElse():\n\tThe name is: "+noName);
	}
	
	private static void orElseGet_showNameOfOptionalHolder() {
		Optional<Holder> empty = Optional.ofNullable(null);
		String noName = empty.map(Holder::getName).orElseGet(()->"Default");
		System.out.println("orElseGet():\n\tThe name is: "+noName);
	}
	
	private static void orElseThrow_showNameOfOptionalHolder() {
		System.out.println("orElseThrow():\n\t...");
		Optional<Holder> empty = Optional.ofNullable(null);
		String noName = empty.map(Holder::getName).orElseThrow(()->new RuntimeException("No Data Available"));
		System.out.println("orElseThrow():\n\tThe name is: "+noName);
	}

	private static void ifPresent_showNameOfOptionalHolder() {
		Optional<String> strOpt = Optional.ofNullable("Present value");
		System.out.print("ifPresent():\n\t");
		strOpt.ifPresent(s -> System.out.println(s));
		
	}

	private static void filter_filteredHolderByName() {
		System.out.print("filter():\n\t");
		Optional<Holder> holderOpt = Optional.ofNullable(Factory.holderSupplier.get());
		holderOpt.filter(h -> h.getAge() < 30).ifPresent(h -> System.out.println("Filtered holder: "+h));
	}

	private static void map_showHolderNameAfterFilter() {
		System.out.print("map():\n\t");
		Optional<Holder> holderOpt = Optional.ofNullable(Factory.holderSupplier.get());
		holderOpt.map(Holder::getName).ifPresent(name -> System.out.println("Mapped holder name: "+name));
	}

	private static void flatmap_showHolderNameAfterFilter() {
		System.out.print("flatMap():\n\t"); 
		Optional<Student> studentOptional =  StudentDataBase.getOptionalStudent();
	      
	      Optional<Bike> bikeOpt = studentOptional
	    		  .filter(student -> student.getGpa()>=3.5)
	    		  .flatMap(Student::getBike);
	      
	      bikeOpt.ifPresent(b -> System.out.println(b));
	      
	}
	
}
