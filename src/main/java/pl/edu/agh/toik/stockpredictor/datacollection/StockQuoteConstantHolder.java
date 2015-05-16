package pl.edu.agh.toik.stockpredictor.datacollection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StockQuoteConstantHolder {

	final Pattern namePattern = Pattern.compile("&lt;Name&gt;(.*)?&lt;/Name&gt;");
	final Pattern shortNamePattern = Pattern.compile("&lt;Symbol&gt;(.*)?&lt;/Symbol&gt;");
	final Pattern valuePattern = Pattern.compile("&lt;Last&gt;(.*)?&lt;/Last&gt;");
	final Pattern datePattern = Pattern.compile("&lt;Date&gt;(.*)?&lt;/Date&gt;&lt;Time&gt;(.*)?&lt;/Time&gt;");
	
	final DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mma");
	
	//TODO put it in some file someday
	final List<String> availableServiecesNames = Arrays.asList("POM", "BBBY", "M", "NFLX", "YUM", "GMCR", "SYMC", "NAVI", "DE", "HRB");
	
}
