package com.ganjx.cinema.util;

import com.ganjx.cinema.basic.security.CustomSecureSubject;


public class SubjectUtils {

	private static ThreadLocal<CustomSecureSubject> subjectThreadLoacal = new ThreadLocal<CustomSecureSubject>();

	public static CustomSecureSubject getCurrentSubject() {
		return subjectThreadLoacal.get();
	}

	public static void setSubject(CustomSecureSubject subject) {
		subjectThreadLoacal.set(subject);
	}

}
