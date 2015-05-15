package pl.edu.agh.toik.stockpredictor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Krzysztof Kicinger on 2015-04-27.
 */
@Controller
public class BaseController {

    @RequestMapping("/")
    public String getIndexPage() {
        return "static/index.html";
    }

}
