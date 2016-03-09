
package com.vion.core.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * <b>功能描述</b> <br>
 * 返回失败的时待序列号的结构体。已json方式序列化
 * @author YUJB
 * @date 2014年5月12日 下午5:53:24
 */
public class JSONFailedReplyModel extends ReplyModel{

	@JsonSerialize(using = ExceptionJsonSerializer.class)
	private Exception exception;
	
	/* (non-Javadoc)
	 * @see com.css.bdp.core.model.ReplyModel#getSuccess()*/
	@Override
	public boolean getSuccess() {
		return false;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
	public static class ExceptionJsonSerializer extends JsonSerializer<Exception> {

		/* (non-Javadoc)
		 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)*/
		@Override
		public void serialize(Exception exception, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			if (exception !=null) {
				StringWriter stringWriter = new StringWriter();
				exception.printStackTrace(new PrintWriter(stringWriter));
				jgen.writeString(stringWriter.toString());
			}
		}
		
	}
	
	
}
