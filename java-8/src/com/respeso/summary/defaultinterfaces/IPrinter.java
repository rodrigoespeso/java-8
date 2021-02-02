package com.respeso.summary.defaultinterfaces;

import java.util.List;

public interface IPrinter {
	
	/*
	 * Declaration of a method. Must to be implemented in a implementation class
	 */
	String concat(List<String> strLst);
	
	/*
	 * Default implementation of a method. It could be overridden too.
	 */
	default void printAll(List<String> strLst) {
		strLst.forEach((s) -> System.out.println(s));
	}
	
	/*
	 * The static methods of an interface can not be overridden.
	 */
	static String size(List<String> strLst) {
		return "Size is "+String.valueOf(strLst.size());
	}
	
}
