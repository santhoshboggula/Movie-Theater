package com.jpmc.theater.model;

import java.time.Duration;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	public static final int MOVIE_CODE_SPECIAL = 1;

	private String title;
	private String description;
	@JsonIgnore
	private Duration runningTime;

	private String duration;
	private double ticketPrice;
	private int specialCode;

	public Movie(final String title, final Duration runningTime, final double ticketPrice, final int specialCode) {
		this.title = title;
		this.runningTime = runningTime;
		this.ticketPrice = ticketPrice;
		this.specialCode = specialCode;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Movie movie = (Movie) o;
		return Double.compare(movie.ticketPrice, ticketPrice) == 0 && Objects.equals(title, movie.title)
				&& Objects.equals(description, movie.description) && Objects.equals(runningTime, movie.runningTime)
				&& Objects.equals(specialCode, movie.specialCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
	}

}