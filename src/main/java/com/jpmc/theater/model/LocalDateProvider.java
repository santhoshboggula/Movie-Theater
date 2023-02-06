package com.jpmc.theater.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class LocalDateProvider {

	private LocalDateProvider() {
	}

	private static class SingletonHelper {
		private static final LocalDateProvider INSTANCE = new LocalDateProvider();
	}

	/**
	 * @return make sure to return singleton instance
	 */
	public static LocalDateProvider getLocalDateProvider() {
		return SingletonHelper.INSTANCE;
	}

	public LocalDate currentDate() {
		return LocalDate.now();
	}
}
