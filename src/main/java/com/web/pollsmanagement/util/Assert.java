package com.web.pollsmanagement.util;


import org.springframework.util.NumberUtils;

import java.util.List;

public class Assert {

	public static boolean isNotNullOrEmpty(final String param){
		return param != null && !param.isEmpty();
	}

	public static boolean isNullOrEmpty(final String param){
		return param == null || param.isEmpty();
	}

	public static boolean isNotNull(final Object param){
		return param != null;
	}

	public static boolean isNull(final String param){
		return param == null;
	}
	
	public static boolean isNull(final Object param){
		return param == null;
	}

	public static boolean isNotEqual(final Double value1, final Double value2){
		return !(value1.equals(value2));
	}

	public static boolean isEmpty(final List<?> param){
		return param == null || param.isEmpty();
	}
}
