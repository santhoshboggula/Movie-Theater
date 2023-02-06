package com.jpmc.theater.exception;

public class MovieTheaterException extends Exception {

	private static final long serialVersionUID = -3711176365517003265L;

	private String exceptionMessage;

	public MovieTheaterException(final Throwable throwable) {
		super();
		if (throwable != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(throwable.getMessage());
			sb.append(throwable.getStackTrace());
			this.exceptionMessage = sb.toString();
		}
	}

}
