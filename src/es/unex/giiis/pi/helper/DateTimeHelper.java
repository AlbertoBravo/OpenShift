package es.unex.giiis.pi.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeHelper {
	
	public static String time2Date(long time){
    	Date date01 = new Date();
		date01.setTime(time);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		String dateFormatted = formatter.format(date01);
		return dateFormatted;
    }

}
