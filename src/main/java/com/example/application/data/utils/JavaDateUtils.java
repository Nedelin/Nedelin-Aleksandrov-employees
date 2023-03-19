package com.example.application.data.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JavaDateUtils {

	public static Date parse(String s) {
		List<String> formatStrings = Arrays.asList("MM-dd-yyyy","MM/dd/yyyy", "MM/dd/yyyy");
		for (String formatString : formatStrings) {
			try {
				return new SimpleDateFormat(formatString).parse(s);
			} catch (ParseException e) {
			}
		}
		return null;
	}
}
