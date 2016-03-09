package com.vion.core.spring;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDateTimeSerializer extends JsonSerializer<Date> {
	
	//
	private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	public CustomDateTimeSerializer(){
		
	}
	
	public CustomDateTimeSerializer(String format){
		if (null != format && format.length()>0 ) {
			this.dateTimeFormat = format;
		}		
	}

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}
}
