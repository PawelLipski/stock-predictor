package pl.edu.agh.toik.stockpredictor.technicalanalysis;

import org.junit.Test;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools;

import java.math.BigDecimal;

/**
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class CandleToolsTest {

    @Test
    public void testCandleType() {
        CandleTools tools = new CandleTools();
        System.out.println(new BigDecimal("1.234567").setScale(4, BigDecimal.ROUND_HALF_UP));
        System.out.println(new BigDecimal("1.232323").setScale(4, BigDecimal.ROUND_HALF_UP));
    }

}
