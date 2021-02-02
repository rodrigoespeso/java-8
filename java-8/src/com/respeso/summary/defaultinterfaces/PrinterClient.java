package com.respeso.summary.defaultinterfaces;

import java.util.Arrays;
import java.util.List;

public class PrinterClient {

	public static void main(String[] args) {
		
		List<String> input = Arrays.asList("One", "Camel", "PC", "Dog", "House");
		
		System.out.println("Using IPrinter. Input list:");
		IPrinter p = new PrinterImpl();
		p.printAll(input);
		
		String result = p.concat(input);
		System.out.println("\nList concatenated:\n"+result);
		
		String sizeStat = IPrinter.size(input);
		System.out.println("\n"+sizeStat);

		/*
		 * To use this method the object must be PrinterImpl because is not visible is
		 * not. Do not to this at home (bad practice...)
		 */
		String sizeMethod = ((PrinterImpl) p).size(input);
		System.out.println("\n"+sizeMethod);
	}

}
