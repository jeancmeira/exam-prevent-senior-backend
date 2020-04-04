package br.com.preventsenior.exam.json.serialization;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.preventsenior.exam.common.DateFormatConstant;

public class CustomDateTimeSerializer extends StdSerializer<Date> {

	private static final long serialVersionUID = 1L;

	public CustomDateTimeSerializer() {
	        this(null);
	    }
	 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomDateTimeSerializer(Class t) {
        super(t);
    }
	
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        try {
        	 
        	gen.writeString(new SimpleDateFormat(DateFormatConstant.DATE_TIME_FORMAT)
        			 	.format(value));
        	 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	  

}
