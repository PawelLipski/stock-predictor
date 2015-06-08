/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core;

import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Dariusz Hudziak
 */

public class StockPredictorContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
       Logger.getLogger("main").warning("contextInitialized");
    }

    
    public void contextDestroyed(ServletContextEvent sce) {
        Logger.getLogger("main").warning("contextDestroed");
        Core.stopDataCollection();
    }
     
    
}
