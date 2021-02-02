package com.respeso.summary.defaultinterfaces;

import java.util.List;
import java.util.stream.Collectors;

public class PrinterImpl implements IPrinter {

	@Override
	public String concat(List<String> strLst) {
		return strLst.stream().collect(Collectors.joining(", "));
	}
	
	/*
	 * This is like "overriding" a static method, which is not allowed.
	 * This method actually calls the static one.
	 */
	public String size(List<String> strLst) {
		return IPrinter.size(strLst)+" using new Size Method";
	}
	
}
