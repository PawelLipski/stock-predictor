package pl.edu.agh.toik.stockpredictor.technicalanalysis;

import org.junit.Test;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.DateTools;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class DateToolsTest {

    @Test
    public void testDayAccuracyDate() {
        Date firstDate = createDate(2000, 0, 1, 10, 15, 00, 00);
        Date secondDate = createDate(2000, 0, 1, 12, 12, 00, 00);
        Date thirdDate = createDate(2000, 0, 1, 23, 15, 00, 00);
        Date firstTest = DateTools.getDayAccurateDate(firstDate);
        Date secondTest = DateTools.getDayAccurateDate(secondDate);
        Date thirdTest = DateTools.getDayAccurateDate(thirdDate);
        assertEquals(firstTest, secondTest);
        assertEquals(firstTest, thirdTest);
        assertEquals(secondTest, thirdTest);
    }

    @Test
    public void testHourAccuracyDate() {
        Date firstDate = createDate(2000, 0, 1, 10, 15, 23, 123);
        Date secondDate = createDate(2000, 0, 1, 10, 15, 52, 445);
        Date thirdDate = createDate(2000, 0, 1, 10, 15, 30, 56);
        Date firstTest = DateTools.getHourAccurateDate(firstDate);
        Date secondTest = DateTools.getHourAccurateDate(secondDate);
        Date thirdTest = DateTools.getHourAccurateDate(thirdDate);
        assertEquals(firstTest, secondTest);
        assertEquals(firstTest, thirdTest);
        assertEquals(secondTest, thirdTest);
    }

    private Date createDate(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

}
