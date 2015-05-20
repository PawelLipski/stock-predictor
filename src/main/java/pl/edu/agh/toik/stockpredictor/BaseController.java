package pl.edu.agh.toik.stockpredictor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.agh.toik.stockpredictor.core.Core;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.DateTools;

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
    
   @RequestMapping("/stock")
   public String getStock() {
       
       Core c = new Core();
       if(!c.candleDAOReady())  return "Missing candle DAO";
       if(!c.stockQuoteDAOReady()) return "Missing stock quote DAO";
       return "Ready to use";
       
   }

}
