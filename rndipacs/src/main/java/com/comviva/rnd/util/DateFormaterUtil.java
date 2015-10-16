package com.comviva.rnd.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormaterUtil {

	public static String formatDateBySample(String sample,Date orignalDate){

		Format formatter = new SimpleDateFormat(sample);
		String s = formatter.format(orignalDate);
		System.out.println(s);
		return s;
}
}