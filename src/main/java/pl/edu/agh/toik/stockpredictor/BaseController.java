package pl.edu.agh.toik.stockpredictor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.agh.toik.stockpredictor.core.Core;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

/**
 * Created by Krzysztof Kicinger on 2015-04-27.
 */
@Controller
public class BaseController {
    private static final Logger l = Logger.getLogger(BaseController.class.getName());
    
    private Core core;
    @Autowired
    @Qualifier("core")
    public void setCore(Core c){
        core = c;
    }
    
    @RequestMapping(value = "/bar")
	@ResponseBody
	public int foo() { 
		return 42;
	}
    
    @RequestMapping("/")
    public String getIndexPage() {
        List<StockQuote> list = new LinkedList();
        
        ListedCompany lc = new ListedCompany("Theory Company","THER");
        list.add(new StockQuote(lc,
                                new Date(System.currentTimeMillis()),
                                new BigDecimal(BigInteger.ONE)));
        list.add(new StockQuote(lc,
                                new Date(System.currentTimeMillis()+60*60*1000),
                                new BigDecimal(BigInteger.ONE)));
        /*core.storeStockQuotes(list);
        
        List<StockQuote> ls = core.getStockQuotes(lc, new Date(System.currentTimeMillis()));
        
        l.info("Stock count: "+ls.size());*/
        
        return "static/index.html";
    }
   
    @RequestMapping(value = "stock",method = RequestMethod.GET)
    @ResponseBody
    public List<StockQuote> getStock(
                                    @RequestParam(value="company") String comp ,    
                                    @RequestParam(value = "n",required = false,defaultValue = "1") int n){
        return core.getLast(new ListedCompany(comp,comp), n);
    }
    
   @RequestMapping(value="/stock",method = RequestMethod.POST)
   @ResponseBody
   public String postStock(@RequestParam(value = "company") String company,
                           @RequestParam(value = "date", required = false) Date date,
                           @RequestParam(value = "value") double v) {
       
       
       
       ListedCompany lc = new ListedCompany(company,company);
       core.storeStockQuotes(Collections.singletonList(new StockQuote(lc,date,BigDecimal.valueOf(v))));
       
       return "Ready to use";
       
   }
   
   @RequestMapping(value="/company",method = RequestMethod.GET)
   @ResponseBody
   public List<ListedCompany> listCompanies() {
       return core.getCompanies();
   }

   
   @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
