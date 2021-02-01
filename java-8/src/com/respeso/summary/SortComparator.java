package com.respeso.summary;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

public class SortComparator {
	
	
	static Consumer<Holder> printHolder = h -> System.out.println(h);
			
	public static void main (String [] args) {
		System.out.println("sort() with Comparator by name:\n");
		Comparator<Holder> nameComparator = Comparator.comparing(Holder::getName);
		List<Holder> holders = Factory.getHolders();		
		holders.sort(nameComparator);
		holders.forEach(printHolder);
		
		// comparatorChaining
		System.out.println("\nsort() chaining with Comparator by gender & age:\n");
		Comparator<Holder> ageComparator = Comparator.comparing(Holder::getAge);
		Comparator<Holder> genderComparator = Comparator.comparing(Holder::getGender);
		holders.sort(genderComparator.thenComparing(ageComparator));
		holders.forEach(printHolder);
		
		// if there are null values, we add other step
		System.out.println("\nsort() chaining with Comparator.nullFirst() by age:\n");

		List<Holder> holdersAndOneNull = Factory.getHoldersAndOneNull();
		Comparator<Holder> ageComparator2 = Comparator.comparing(Holder::getAge);
		Comparator<Holder> nullsFirstAgeComparator = Comparator.nullsFirst(ageComparator2);
		holdersAndOneNull.sort(nullsFirstAgeComparator);
		holdersAndOneNull.forEach(printHolder);

		System.out.println("\nsort() chaining with Comparator.nullLast() by age:\n");
		Comparator<Holder> nullsLastAgeComparator = Comparator.nullsLast(ageComparator);
		holdersAndOneNull.sort(nullsLastAgeComparator);
		holdersAndOneNull.forEach(printHolder);

	}
	
}
