package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

public interface ICoreCandlestickChartService {
	List<Candle> getCandlestickChart(ListedCompany listedCompany, Date dayFrom, Date dayTo);
}
