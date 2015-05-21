package pl.edu.agh.toik.stockpredictor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Formation;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

@Controller
public class RestController {	

	// http://localhost:8080/StockPredictor/foo?date=2015-05-15
	@RequestMapping(value = "/foo")
	@ResponseBody
	public CandlestickChart foo(@RequestParam(value="date") Date s) {
		CandlestickChart chart = new CandlestickChart();
		ListedCompany listedCompany = new ListedCompany("Example", "EXA");
		chart.setListedCompany(listedCompany);
		chart.setStartDay(new Date(2015, 4, 10));
		chart.setEndDay(new Date(2015, 4, 15));
		chart.setCandles(new ArrayList<Candle>());
		chart.setFormations(new ArrayList<Formation>());
		return chart;
	}
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
