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

	public CustomJacksonObjectMapper() {
		super();
		System.out.println("CustomJacksonObjectMapper()");
		configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@PostConstruct
	public void init() throws Exception {
		super.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);

		// this one doesn't work at all, it's necessary to create and register a
		// factory
		// getSerializationConfig().withDateFormat(new SimpleDateFormat(mask));

		// I am using Jackson 1.9 asl
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

				jgen.writeString(new SimpleDateFormat("yyyy-MM-dd")
						.format(value));
			}
		});
		
		this.setSerializerFactory(factory);
		System.out.println("init done");
	}
}
