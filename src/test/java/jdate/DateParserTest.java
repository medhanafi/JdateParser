package jdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.comoressoft.jdate.loader.JDParser;

class DateParserTest {

	@Test
	void test() {
		JDParser dp =new JDParser();
		dp.parse("03/22/1999");
	}

}
