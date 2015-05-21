package pl.edu.agh.toik.stockpredictor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class CustomJacksonObjectMapper extends ObjectMapper {

	@PostConstruct
	public void init() throws Exception {
		super.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);

		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addSpecificMapping(Date.class, new JsonSerializer<Date>() {

			@Override
			public Class<Date> handledType() {
				return Date.class;
			}

			@Override
			public void serialize(Date value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {

				jgen.writeString(new SimpleDateFormat("yyyy-MM-dd").format(value));
			}
		});
		
		this.setSerializerFactory(factory);		
	}
}
