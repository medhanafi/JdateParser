package com.comoressoft.jdate.loader;

import com.comoressoft.jdate.exceptions.JDException;

public class JDBuilder {

	public static void build() throws JDException {
		new JDLoader();
		new JDParser();
	}
}
