package com.comoressoft.jdate.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comoressoft.jdate.AbstractService;
import com.comoressoft.jdate.constants.JDConstant;
import com.comoressoft.jdate.exceptions.JDException;

/**
 * 
 * Load the necessary resources used by parsing process
 *
 * @author MHA14633
 */
public class JDLoader extends AbstractService implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6772922457331124589L;
	public static final Logger LOGGER = LoggerFactory.getLogger(JDLoader.class);

	private URL uri;
	private List<String> dateFormats = new ArrayList<>();

	/**
	 * Constructor
	 *
	 */
	public JDLoader() {
		this.dateFormats.addAll(this.load());
	}

	/**
	 * Load external resources
	 */
	public List<String> load() {
		List<String> list = new ArrayList<>();
		this.uri = this.getClass().getClassLoader().getResource(JDConstant.DATA_PATH);
		try (BufferedReader br = Files.newBufferedReader(Paths.get(this.uri.getPath()))) {
			list = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			this.getLogger().warn("File not found. Path : {} {}", uri != null ? uri.getPath() : "not found", e);
			new JDException(String.format("File not found Path: %s", uri != null ? uri.getPath() : "not found"), e);
		}
		return list;
	}

	/**
	 * Format external resources
	 * 
	 * @return List of triple element (Date format, regex pettern, Locale)
	 */
	public List<Triple<String, Pattern, Locale>> getRessources() {
		List<Triple<String, Pattern, Locale>> listRessource = new ArrayList<>();
		boolean errorFormat = false;
		for (String lineformat : this.dateFormats) {
			if (StringUtils.contains(lineformat, JDConstant.SPLIT_CHAR)) {
				final String[] str = lineformat.split(JDConstant.SPLIT_CHAR);
				listRessource.add(Triple.of(str[0], Pattern.compile(str[1]), getLocal(str[2])));
			} else {
				errorFormat = true;
			}
		}
		if (errorFormat) {
			LOGGER.error("Error: Date format, Regex and Local format should separated by '"+JDConstant.SPLIT_CHAR+"'");
		}

		return listRessource;
	}

	/**
	 * Get Local from ISO code
	 * 
	 * @param countryCode
	 * @return Locale
	 */
	public Locale getLocal(final String countryCode) {
		if (!Objects.isNull(countryCode))
			return Locale.forLanguageTag(getISOCode(countryCode));
		return Locale.getDefault();
	}

	/**
	 * Get ISO code from country code
	 * 
	 * @param countryCode
	 * @return
	 */
	private String getISOCode(String countryCode) {
		if (countryCode.contains("_")) {
			return countryCode.substring(countryCode.indexOf("_") + 1).toLowerCase();
		}
		return countryCode;
	}
}
