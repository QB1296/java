package com.vion.core.exception;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年6月16日 上午9:12:29
 */
public class NoSupportedException extends SpiderRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @param message
	 * @param objects
	 */
	public NoSupportedException(String message, Object... objects) {
		super(message, objects);
	}

	/**
	 * @param message
	 * @param cause
	 * @param objects
	 */
	public NoSupportedException(String message, Throwable cause,
			Object... objects) {
		super(message, cause, objects);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NoSupportedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoSupportedException(Throwable cause) {
		super(cause);
	}
	
	
	

}
