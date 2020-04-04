package br.com.preventsenior.exam.json.serialization;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.preventsenior.exam.common.DateFormatConstant;

public class CustomDateTimeDeserializer extends StdDeserializer<Date> {

	private static final long serialVersionUID = 1L;

    public CustomDateTimeDeserializer () {
        this(null);
    }

    protected CustomDateTimeDeserializer (Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser arg0, DeserializationContext arg1)
        throws IOException, JsonProcessingException {
    	
        try {
			
        	return  new  SimpleDateFormat(DateFormatConstant.DATE_TIME_FORMAT)
						.parse(arg0.getValueAsString());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
