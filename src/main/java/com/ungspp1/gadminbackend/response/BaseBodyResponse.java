package com.ungspp1.gadminbackend.response;

import lombok.Data;

@Data
public class BaseBodyResponse<T> {
	public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	public static final String LOCAL_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String LOCAL_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	protected T result;
	protected BaseBodyError error;

	public boolean hasResult() {
		return this.result != null;
	}

	public boolean hasError() {
		return this.error != null;
	}

	public static <T> BaseBodyResponse<T> result(T result) {
		BaseBodyResponse<T> response = new BaseBodyResponse<>();
		response.setResult(result);
		return response;
	}

	public static <T, R extends BaseBodyResponse<T>> R result(T result, Class<R> clazz) {
		try {
			R response = clazz.getDeclaredConstructor().newInstance();
			response.setResult(result);
			return response;
		} catch (Exception e) { throw new RuntimeException(e); }
	}

	public static <T> BaseBodyResponse<T> error(BaseBodyError error) {
		BaseBodyResponse<T> response = new BaseBodyResponse<>();
		response.setError(error);
		return response;
	}

	public static <R extends BaseBodyResponse<?>> R error(BaseBodyError error, Class<R> clazz) {
		try {
			R response = clazz.getDeclaredConstructor().newInstance();
			response.setError(error);
			return response;
		} catch (Exception e) { throw new RuntimeException(e); }
	}
}
