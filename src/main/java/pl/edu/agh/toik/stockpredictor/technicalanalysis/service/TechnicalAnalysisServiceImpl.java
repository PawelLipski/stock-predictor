package pl.edu.agh.toik.stockpredictor.technicalanalysis.service;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.DateTools;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.FormationTools;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Component;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
@Component
public class TechnicalAnalysisServiceImpl implements ITechnicalAnalysisService {

    private static Comparator<StockQuote> SHARE_DATA_COMPARATOR = new Comparator<StockQuote>() {
        @Override
        public int compare(StockQuote o1, StockQuote o2) {
            return o1.getDateAndTime().compareTo(o2.getDateAndTime());
        }
    };

    private static Comparator<Candle> CANDLE_COMPARATOR = new Comparator<Candle>() {
        @Override
        public int compare(Candle o1, Candle o2) {
            return o1.getDay().compareTo(o2.getDay());
        }
    };

    @Override
    public List<Candle> getCandles(List<StockQuote> shareData) {
        createHourAccurateDates(shareData);
        Collections.sort(shareData, SHARE_DATA_COMPARATOR);
        Map<Date, List<StockQuote>> dayToShareDataMap = createDayToListOfShareDataMap(shareData);
        return createCandles(dayToShareDataMap);
    }

    @Override
    public CandlestickChart createCandlestickChart(ListedCompany listedCompany, List<Candle> candles) {
        Collections.sort(candles, CANDLE_COMPARATOR);
        CandlestickChart candlestickChart = new CandlestickChart();
        candlestickChart.setListedCompany(listedCompany);
        candlestickChart.setCandles(candles);
        candlestickChart.setStartDay(candles.get(0).getDay());
        candlestickChart.setEndDay(candles.get(candles.size() - 1).getDay());
        candlestickChart.setFormations(FormationTools.getFormations(candles));
        return candlestickChart;
    }

    private void createHourAccurateDates(List<StockQuote> shareData) {
        for(StockQuote sd : shareData) {
            sd.setDateAndTime(DateTools.getHourAccurateDate(sd.getDateAndTime()));
        }
    }

    private Map<Date, List<StockQuote>> createDayToListOfShareDataMap(List<StockQuote> shareData) {
        Map<Date, List<StockQuote>> dayToShareDataMap = new HashMap<Date, List<StockQuote>>();
        for(StockQuote sd : shareData) {
            Date dayAccurateDate = DateTools.getDayAccurateDate(sd.getDateAndTime());
            if(dayToShareDataMap.containsKey(dayAccurateDate)) {
                dayToShareDataMap.get(dayAccurateDate).add(sd);
            } else {
                dayToShareDataMap.put(dayAccurateDate, Arrays.asList(sd));
            }
        }
        return dayToShareDataMap;
    }

    private List<Candle> createCandles(Map<Date, List<StockQuote>> dayToShareDataMap) {
        List<Candle> candles = new ArrayList<Candle>();
        for(Date date : dayToShareDataMap.keySet()) {
            List<StockQuote> shareDataList = dayToShareDataMap.get(date);
            Candle candle = new Candle();
            candle.setOpeningPrice(shareDataList.get(0).getValue());
            candle.setClosingPrice(shareDataList.get(shareDataList.size() - 1).getValue());
            candle.setMaxPrice(BigDecimal.ZERO);
            candle.setMinPrice(BigDecimal.ZERO);
            for(StockQuote sd : dayToShareDataMap.get(date)) {
                if(sd.getValue().compareTo(candle.getMaxPrice()) > 0) {
                    candle.setMaxPrice(sd.getValue());
                }
                if(sd.getValue().compareTo(candle.getMinPrice()) < 0) {
                    candle.setMinPrice(sd.getValue());
                }
            }
            candle.setDay(date);
            candle.setColor(CandleTools.getCandleColor(candle));
            candle.setType(CandleTools.getCandleType(candle));
            candle.setListedCompany(shareDataList.get(0).getListedCompany());
            candles.add(candle);
        }
        return candles;
    }

}
