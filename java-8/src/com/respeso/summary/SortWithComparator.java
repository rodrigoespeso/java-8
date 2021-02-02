package com.respeso.summary;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

/**
 * Examples of Java 8 comparing() method of Comparator interface.
 * 
 * @author Rodrigo Espeso
 *
 */
public class SortWithComparator {
	
	
	static Consumer<Holder> printHolder = h -> System.out.println(h);
	static List<Holder> holders = Factory.getHolders();		
			
	public static void main (String [] args) {
		sort_holdersByName();
		sort_thenCommparing_holdersByGenderAndAge();
		sort_nullsFirst_holdersByAge();
		sort_nullsLast_holdersByAge();
	}

	private static void sort_holdersByName() {
		System.out.println("sort() by name:\n");
		Comparator<Holder> nameComparator = Comparator.comparing(Holder::getName);
		holders.sort(nameComparator);
		holders.forEach(printHolder);
	}

	private static void sort_thenCommparing_holdersByGenderAndAge() {
		// use of chaining with comparators
		System.out.println("\nsort() & thenComparing() by gender & age:\n");
		Comparator<Holder> ageComparator = Comparator.comparing(Holder::getAge);
		Comparator<Holder> genderComparator = Comparator.comparing(Holder::getGender);
		holders.sort(genderComparator.thenComparing(ageComparator));
		holders.forEach(printHolder);
	}

	private static void sort_nullsFirst_holdersByAge() {
		// if there are null values, we add other step
		System.out.println("\nsort() with Comparator.nullsFirst() by age:\n");
		List<Holder> holdersAndOneNull = Factory.getHoldersAndOneNull();
		Comparator<Holder> ageComparator = Comparator.comparing(Holder::getAge);
		Comparator<Holder> nullsFirstAgeComparator = Comparator.nullsFirst(ageComparator);
		holdersAndOneNull.sort(nullsFirstAgeComparator);
		holdersAndOneNull.forEach(printHolder);
	}

	private static void sort_nullsLast_holdersByAge() {
		System.out.println("\nsort() with Comparator.nullsLast() by age:\n");
		List<Holder> holdersAndOneNull = Factory.getHoldersAndOneNull();
		Comparator<Holder> ageComparator = Comparator.comparing(Holder::getAge);
		Comparator<Holder> nullsLastAgeComparator = Comparator.nullsLast(ageComparator);
		holdersAndOneNull.sort(nullsLastAgeComparator);
		holdersAndOneNull.forEach(printHolder);
	}
	
}
