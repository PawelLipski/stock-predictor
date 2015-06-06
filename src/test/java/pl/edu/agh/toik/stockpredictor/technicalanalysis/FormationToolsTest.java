package pl.edu.agh.toik.stockpredictor.technicalanalysis;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Formation;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.FormationType;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.FormationTools;

public class FormationToolsTest {

	@Test
	public void testBessaReversion() {
		Date date = new Date(2015, 1, 1);
		FormationType type = FormationType.BESSA_REVERSION;
		Candle candle = new Candle(new ListedCompany("ala", "A"),
				new BigDecimal(100), new BigDecimal(0), new BigDecimal(90),
				new BigDecimal(10), date);
		LinkedList<Candle> candles = new LinkedList<Candle>();
		candles.add(candle);
		List<Formation> expected = new LinkedList<Formation>();
		expected.add(new Formation(date, date, type, candles));
		List<Formation> result = FormationTools.getFormations(candles);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testHossaReversion() {
		Date date = new Date(2015, 1, 1);
		FormationType type = FormationType.HOSSA_REVERSION;
		Candle candle = new Candle(new ListedCompany("ala", "A"),
				new BigDecimal(100), new BigDecimal(0), new BigDecimal(10),
				new BigDecimal(90), date);
		LinkedList<Candle> candles = new LinkedList<Candle>();
		candles.add(candle);
		List<Formation> expected = new LinkedList<Formation>();
		expected.add(new Formation(date, date, type, candles));
		List<Formation> result = FormationTools.getFormations(candles);
		assertTrue(result.equals(expected));
	}

	@Test
	public void TestEngulfingBessaPattern() {
		Date date1 = new Date(2015, 1, 1);
		Date date2 = new Date(2015, 1, 2);
		ListedCompany company = new ListedCompany("ala", "A");

		Candle candle1 = new Candle(company, new BigDecimal(100),
				new BigDecimal(20), new BigDecimal(90), new BigDecimal(30),
				date1);
		Candle candle2 = new Candle(company, new BigDecimal(120),
				new BigDecimal(0), new BigDecimal(20), new BigDecimal(100),
				date2);

		LinkedList<Candle> candles1 = new LinkedList<Candle>();
		LinkedList<Candle> candles3 = new LinkedList<Candle>();
		candles1.add(candle1);
		candles3.add(candle1);
		candles3.add(candle2);
		List<Formation> expected = new LinkedList<Formation>();
		
		expected.add(new Formation(date1, date1, FormationType.BESSA_REVERSION, candles1));
		expected.add(new Formation(date1, date2, FormationType.ENGULFING_HOSSA_PATTERN, candles3));
		
		List<Formation> result = FormationTools.getFormations(candles3);
		assertTrue(result.equals(expected));
	}
	
	@Test
	public void TestEngulfingHossaPattern() {
		Date date1 = new Date(2015, 1, 1);
		Date date2 = new Date(2015, 1, 2);
		ListedCompany company = new ListedCompany("ala", "A");

		Candle candle1 = new Candle(company, new BigDecimal(100),
				new BigDecimal(20), new BigDecimal(30), new BigDecimal(90),
				date1);
		Candle candle2 = new Candle(company, new BigDecimal(120),
				new BigDecimal(0), new BigDecimal(100), new BigDecimal(20),
				date2);

		LinkedList<Candle> candles1 = new LinkedList<Candle>();
		LinkedList<Candle> candles3 = new LinkedList<Candle>();
		candles1.add(candle1);
		candles3.add(candle1);
		candles3.add(candle2);
		List<Formation> expected = new LinkedList<Formation>();
		
		expected.add(new Formation(date1, date1, FormationType.HOSSA_REVERSION, candles1));
		expected.add(new Formation(date1, date2, FormationType.ENGULFING_BESSA_PATTERN, candles3));
		
		List<Formation> result = FormationTools.getFormations(candles3);
		assertTrue(result.equals(expected));
	}
	
	@Test
	public void TestBullishKickerPattern() {
		Date date1 = new Date(2015, 1, 1);
		Date date2 = new Date(2015, 1, 2);
		Date date3 = new Date(2015, 1, 3);
		Date date4 = new Date(2015, 1, 4);
		ListedCompany company = new ListedCompany("ala", "A");

		Candle candle1 = new Candle(company, new BigDecimal(300),
				new BigDecimal(200), new BigDecimal(290), new BigDecimal(200),
				date1);
		Candle candle2 = new Candle(company, new BigDecimal(200),
				new BigDecimal(100), new BigDecimal(190), new BigDecimal(110),
				date2);
		Candle candle3 = new Candle(company, new BigDecimal(100),
				new BigDecimal(0), new BigDecimal(90), new BigDecimal(10),
				date3);
		Candle candle4 = new Candle(company, new BigDecimal(200),
				new BigDecimal(100), new BigDecimal(110), new BigDecimal(190),
				date4);
		
		LinkedList<Candle> allCandles = new LinkedList<Candle>();
		LinkedList<Candle> candles1 = new LinkedList<Candle>();
		candles1.add(candle1);
		LinkedList<Candle> candles2 = new LinkedList<Candle>();
		candles2.add(candle2);
		LinkedList<Candle> candles3 = new LinkedList<Candle>();
		candles3.add(candle3);
		LinkedList<Candle> candles4 = new LinkedList<Candle>();
		candles4.add(candle4);
		
		allCandles.add(candle1);
		allCandles.add(candle2);
		allCandles.add(candle3);
		allCandles.add(candle4);
		
		List<Formation> expected = new LinkedList<Formation>();
		expected.add(new Formation(date1, date1, FormationType.BESSA_REVERSION, candles1));
		expected.add(new Formation(date2, date2, FormationType.BESSA_REVERSION, candles2));
		expected.add(new Formation(date3, date3, FormationType.BESSA_REVERSION, candles3));
		expected.add(new Formation(date4, date4, FormationType.HOSSA_REVERSION, candles4));
		expected.add(new Formation(date1, date4, FormationType.BULLISH_KICKER_PATTERN, allCandles));
		List<Formation> result = FormationTools.getFormations(allCandles);
		
		assertTrue(result.equals(expected));
	}
	
	@Test
	public void TestBearishKickerPattern() {
		Date date1 = new Date(2015, 1, 1);
		Date date2 = new Date(2015, 1, 2);
		Date date3 = new Date(2015, 1, 3);
		Date date4 = new Date(2015, 1, 4);
		ListedCompany company = new ListedCompany("ala", "A");

		Candle candle1 = new Candle(company, new BigDecimal(100),
				new BigDecimal(0), new BigDecimal(10), new BigDecimal(90),
				date1);
		Candle candle2 = new Candle(company, new BigDecimal(200),
				new BigDecimal(100), new BigDecimal(110), new BigDecimal(190),
				date2);
		Candle candle3 = new Candle(company, new BigDecimal(300),
				new BigDecimal(200), new BigDecimal(210), new BigDecimal(290),
				date3);
		Candle candle4 = new Candle(company, new BigDecimal(200),
				new BigDecimal(100), new BigDecimal(190), new BigDecimal(110),
				date4);
		
		LinkedList<Candle> allCandles = new LinkedList<Candle>();
		LinkedList<Candle> candles1 = new LinkedList<Candle>();
		candles1.add(candle1);
		LinkedList<Candle> candles2 = new LinkedList<Candle>();
		candles2.add(candle2);
		LinkedList<Candle> candles3 = new LinkedList<Candle>();
		candles3.add(candle3);
		LinkedList<Candle> candles4 = new LinkedList<Candle>();
		candles4.add(candle4);
		
		allCandles.add(candle1);
		allCandles.add(candle2);
		allCandles.add(candle3);
		allCandles.add(candle4);
		
		List<Formation> expected = new LinkedList<Formation>();
		expected.add(new Formation(date1, date1, FormationType.HOSSA_REVERSION, candles1));
		expected.add(new Formation(date2, date2, FormationType.HOSSA_REVERSION, candles2));
		expected.add(new Formation(date3, date3, FormationType.HOSSA_REVERSION, candles3));
		expected.add(new Formation(date4, date4, FormationType.BESSA_REVERSION, candles4));
		expected.add(new Formation(date1, date4, FormationType.BEARISH_KICKER_PATTERN, allCandles));
		
		List<Formation> result = FormationTools.getFormations(allCandles);
		
		assertTrue(result.equals(expected));
	}
	
	
}
