package pl.edu.agh.toik.stockpredictor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@Component
public class ConverterRegister {
	
	@Autowired
	private AnnotationMethodHandlerAdapter adapter;

	private HttpMessageConverter<?>[] messageConverters;
	
	public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
		this.messageConverters = messageConverters;
	}

	@PostConstruct
	public void bindMessageConverters() {
		adapter.setMessageConverters(messageConverters);
	}

}
