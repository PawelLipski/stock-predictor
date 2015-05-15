package pl.edu.agh.toik.stockpredictor.technicalanalysis.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class DateTools {

    public static Date getDayAccurateDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getHourAccurateDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
