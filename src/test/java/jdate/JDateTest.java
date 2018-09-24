package jdate;

import org.junit.Assert;
import org.junit.Test;

import com.comoressoft.jdate.AbstractService;
import com.comoressoft.jdate.exceptions.JDException;
import com.comoressoft.jdate.loader.JDBuilder;
import com.comoressoft.jdate.loader.JDLoader;
import com.comoressoft.jdate.loader.JDParser;

public class JDateTest extends AbstractService {

	
	@Test
	public void testParse() throws JDException {
		JDBuilder.build();
		Assert.assertEquals("Mon Mar 22 05:06:00 CET 1999", JDParser.parse("03-22-99 5:06 AM").toString());
		Assert.assertEquals("Mon Mar 22 00:00:00 CET 1999", JDParser.parse("19990322+0100").toString());
		Assert.assertEquals("Mon Mar 22 00:00:00 CET 1999", JDParser.parse("19990322").toString());
		//Assert.assertEquals("Mon Mar 22 05:06:07 CET 1999", JDParser.parse("1999-22-03 AD at 05:06:07 PDT").toString());
		Assert.assertEquals("Mon Mar 22 00:00:00 CET 1999", JDParser.parse("1999-03-22+01:00").toString());
		//Assert.assertEquals("Mon Mar 22 05:06:00 CET 1999", JDParser.parse("1999-03-22T05:06:07.000[Europe/Paris]").toString());
		Assert.assertEquals("Mon Mar 22 05:06:07 CET 1999", JDParser.parse("1999-03-22T05:06:07.000").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22T05:06:07").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22T05:06:07.000Z").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22T05:06:07.000+01:00").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22T05:06:07+01:00").toString());
//		Assert.assertEquals("", JDParser.parse("1999-081+01:00").toString());
//		Assert.assertEquals("", JDParser.parse("1999W132").toString());
//		Assert.assertEquals("", JDParser.parse("1999-W13-2").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22T05:06:07.000+01:00[Europe/Paris]").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22T05:06:07+01:00[Europe/Paris]").toString());
//		Assert.assertEquals("", JDParser.parse("22.3.99").toString());
//		Assert.assertEquals("", JDParser.parse("22.3.99 5.06").toString());
//		Assert.assertEquals("", JDParser.parse("22.3.1999 5.06.07").toString());
//		Assert.assertEquals("", JDParser.parse("22.3.1999 5:06:07").toString());
//		Assert.assertEquals("", JDParser.parse("22-03-99").toString());
//		Assert.assertEquals("", JDParser.parse("22-03-99 05:06").toString());
//		Assert.assertEquals("", JDParser.parse("22-03-1999 05:06:07").toString());
//		Assert.assertEquals("", JDParser.parse("22.03.99").toString());
//		Assert.assertEquals("", JDParser.parse("22. März 1999").toString());
//		Assert.assertEquals("", JDParser.parse("Montag, 22. März 1999").toString());
//		Assert.assertEquals("", JDParser.parse("22.03.1999").toString());
//		Assert.assertEquals("", JDParser.parse("22.03.99 05:06").toString());
//		Assert.assertEquals("", JDParser.parse("22. März 1999 05:06:07 MEZ").toString());
//		Assert.assertEquals("", JDParser.parse("22.03.1999 05:06:07").toString());
//		Assert.assertEquals("", JDParser.parse("22.03.99 05:06:07").toString());
//		Assert.assertEquals("", JDParser.parse("22.03.1999 05:06").toString());
//		Assert.assertEquals("", JDParser.parse("Montag, 22. März 1999 05:06 Uhr MEZ").toString());
//		Assert.assertEquals("", JDParser.parse("22-Mar-1999").toString());
//		Assert.assertEquals("", JDParser.parse("22/03/99 5:06 AM").toString());
//		Assert.assertEquals("", JDParser.parse("Monday, March 22, 1999 5:06:07 o'clock AM CET").toString());
//		Assert.assertEquals("", JDParser.parse("22-Mar-1999 5:06:07 AM").toString());
//		Assert.assertEquals("", JDParser.parse("22 March 1999").toString());
//		Assert.assertEquals("", JDParser.parse("Monday, 22 March 1999").toString());
//		Assert.assertEquals("", JDParser.parse("22-Mar-1999").toString());
//		Assert.assertEquals("", JDParser.parse("22 March 1999 05:06:07 CET").toString());
//		Assert.assertEquals("", JDParser.parse("Monday, 22 March 1999 05:06:07 o'clock CET").toString());
//		Assert.assertEquals("", JDParser.parse("22-Mar-1999 05:06:07").toString());
//		Assert.assertEquals("", JDParser.parse("22-Mar-99 05.06.07.000000888 AM").toString());
//		Assert.assertEquals("", JDParser.parse("3/22/99").toString());
//		Assert.assertEquals("", JDParser.parse("03/11/22").toString());
//		Assert.assertEquals("", JDParser.parse("03-22-99").toString());
//		Assert.assertEquals("", JDParser.parse("3-22-99").toString());
//		Assert.assertEquals("", JDParser.parse("Mar 22, 1999").toString());
//		Assert.assertEquals("", JDParser.parse("March 22, 1999").toString());
//		Assert.assertEquals("", JDParser.parse("Monday, March 22, 1999").toString());
//		Assert.assertEquals("", JDParser.parse("Mar 22 1999").toString());
//		Assert.assertEquals("", JDParser.parse("March 22 1999").toString());
//		Assert.assertEquals("", JDParser.parse("03-22-1999").toString());
//		Assert.assertEquals("", JDParser.parse("3-22-1999").toString());
//		Assert.assertEquals("", JDParser.parse("1999-03-22+01:00").toString());
//		Assert.assertEquals("", JDParser.parse("22/03/1999").toString());
//		Assert.assertEquals("", JDParser.parse("22/3/1999").toString());
//		Assert.assertEquals("", JDParser.parse("03/22/1999").toString());
//		Assert.assertEquals("", JDParser.parse("3/22/1999").toString());
//		Assert.assertEquals("", JDParser.parse("1999/3/22").toString());
//		Assert.assertEquals("", JDParser.parse("3/22/99 5:06 AM").toString());
//		Assert.assertEquals("", JDParser.parse("03/22/99 5:06 AM").toString());
//		Assert.assertEquals("", JDParser.parse("03-22-99 5:06 AM").toString());
//		Assert.assertEquals("", JDParser.parse("3-22-99 5:06 AM").toString());

	}

	@Test
	public void testLoggerInfoEnabled() {
		Assert.assertTrue(getLogger().isInfoEnabled());
	}

	@Test
	public void testJbuild() {
		Assert.assertNotNull(new JDBuilder());
	}

	@Test(expected = JDException.class)
	public void testIOException() throws JDException {
		JDLoader.getData("file_data.kom");
	}

}
