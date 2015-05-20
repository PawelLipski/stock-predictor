package pl.edu.agh.toik.stockpredictor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {	
	@RequestMapping(value = "/foo")
	@ResponseBody
	public int foo() { 
		return 42;
	}
}
