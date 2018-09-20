package jdate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.comoressoft.jdate.loader.JDBuilder;
import com.comoressoft.jdate.loader.JDParser;

public class JDateTest {

	@Before
	public void setUp() throws Exception {
		JDBuilder.build();
	}

	@Test
	public void testParse() {
		Assert.assertEquals("Mon Mar 22 00:00:00 CET 1999", JDParser.parse("03/22/1999").toString());
	}

}
