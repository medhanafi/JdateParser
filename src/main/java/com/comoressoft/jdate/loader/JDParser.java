package com.comoressoft.jdate.loader;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comoressoft.jdate.exceptions.JDException;

/**
 * 
 * (Description)
 *
 * @since 29 août 2016
 * @author MHA14633
 */
public class JDParser implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4442478725575405320L;
	public static final Logger LOGGER = LoggerFactory.getLogger(JDParser.class);
	private JDLoader dateLoader;
	private static List<Triple<String, Pattern, Locale>> resources;

	public JDParser() {
		this.dateLoader = new JDLoader();
		resources = this.dateLoader.getRessources();

	}

	public static Date parse(String inputDate) {
		Date outputDate = null;
		for(Triple<String, Pattern, Locale> ressource:resources) {
			Matcher matcher = ressource.getMiddle().matcher(inputDate);
			if (matcher.find()) {
				outputDate=parseDate(outputDate, matcher.group(), new SimpleDateFormat(ressource.getLeft()), ressource.getRight());
			}
			
			if(outputDate != null)
				return outputDate;
		}
		return outputDate;  

		
	}

	private static Date parseDate(Date outputDate, final String inputDate, final SimpleDateFormat simpleDateFormat,
			final Locale locale) {
		if (outputDate == null) {
			try {
				outputDate = simpleDateFormat.parse(inputDate);
			} catch (ParseException e) {
				LOGGER.error("Unparseable Date {} using Local {} " , inputDate,locale);
				new JDException(String.format("Unparseable Date %s using Locale %s " + e, inputDate, locale));
			}
		}

		return outputDate;
	}


}
