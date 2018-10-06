package unitfunc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MinusDate {
	
	public static int diffDay(String checkin, String checkout) {
		
		int diffInt;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(checkin);
			date2 = sdf.parse(checkout);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long diffInMillies = (date2.getTime() - date1.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		diffInt = (int) diff;
		return diffInt;
	}
}
