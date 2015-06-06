package pl.edu.agh.toik.stockpredictor.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.agh.toik.stockpredictor.core.ICoreCandlestickChartService;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

@Controller
public class CandlestickChartController {

	@Autowired
	private ICoreCandlestickChartService chartService;	
	
	// For example:
	// http://localhost:8080/StockPredictor/getCandlestickCharts?companyShortName=EXA&dayFrom=2015-05-10&dayTo=2015-05-20
	@RequestMapping(value = "/getCandlestickCharts")
	@ResponseBody
	public CandlestickChart getCandlestickCharts(
			HttpServletResponse response,
			@RequestParam(value="companyShortName") String companyShortName,
			@RequestParam(value="dayFrom") Date dayFrom,
			@RequestParam(value="dayTo") Date dayTo) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		ListedCompany company = new ListedCompany("", companyShortName);
		return chartService.getCandlestickChart(company, dayFrom, dayTo);				
	}
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
