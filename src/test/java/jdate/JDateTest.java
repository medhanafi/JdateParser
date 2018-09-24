package jdate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.comoressoft.jdate.AbstractService;
import com.comoressoft.jdate.loader.JDBuilder;
import com.comoressoft.jdate.loader.JDParser;

public class JDateTest extends AbstractService {

	@Before
	public void setUp() throws Exception {
		JDBuilder.build();
	}

	@Test
	public void testParse() {
		Assert.assertEquals("Mon Mar 22 05:06:00 CET 1999", JDParser.parse("03-22-99 5:06 AM").toString());
	}
	
	@Test
	public void testLoggerInfoEnabled() {
		Assert.assertTrue(getLogger().isInfoEnabled());
	}

	@Test
	public void testJbuild(){
		Assert.assertNotNull(new JDBuilder());
	}
	
}
