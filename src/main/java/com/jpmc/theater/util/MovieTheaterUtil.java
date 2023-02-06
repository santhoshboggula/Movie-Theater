package com.jpmc.theater.util;

import static com.jpmc.theater.model.Movie.MOVIE_CODE_SPECIAL;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.theater.model.LocalDateProvider;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.ShowDetails;

@Component
public class MovieTheaterUtil {
	private static final String TURNING_RED = "Turning Red";

	private static final String THE_BATMAN = "The Batman";

	private static final String SPIDER_MAN_NO_WAY_HOME = "Spider-Man: No Way Home";

	@Autowired
	private LocalDateProvider provider;

	public List<ShowDetails> moviesSchedule() {
		final var spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME, Duration.ofMinutes(90), 12.5, 1);
		final var turningRed = new Movie(TURNING_RED, Duration.ofMinutes(85), 11, 0);
		final var theBatMan = new Movie(THE_BATMAN, Duration.ofMinutes(95), 9, 0);
		final var spiderManSecondShow = new Movie(SPIDER_MAN_NO_WAY_HOME, Duration.ofMinutes(90), 12.5, 1);
		final var turningRedSecondShow = new Movie(TURNING_RED, Duration.ofMinutes(85), 11, 0);
		final var theBatManSecondShow = new Movie(THE_BATMAN, Duration.ofMinutes(95), 9, 0);
		final var spiderManThirdShow = new Movie(SPIDER_MAN_NO_WAY_HOME, Duration.ofMinutes(90), 12.5, 1);
		final var turningRedThirdShow = new Movie(TURNING_RED, Duration.ofMinutes(85), 11, 0);
		final var theBatManThirdShow = new Movie(THE_BATMAN, Duration.ofMinutes(95), 9, 0);
		return List.of(new ShowDetails(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
				new ShowDetails(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
				new ShowDetails(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
				new ShowDetails(turningRedSecondShow, 4,
						LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
				new ShowDetails(spiderManSecondShow, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
				new ShowDetails(theBatManSecondShow, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
				new ShowDetails(turningRedThirdShow, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
				new ShowDetails(spiderManThirdShow, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
				new ShowDetails(theBatManThirdShow, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));

	}

	public double calculateTicketPrice(final ShowDetails showDetails) {
		final var initialPrice = showDetails.getMovie().getTicketPrice();
		final var sequenceOfTheDay = showDetails.getSequenceOfTheDay();
		final var specialCode = showDetails.getMovie().getSpecialCode();
		final var hourOfTheDay = showDetails.getShowStartTime().getHour();

		return initialPrice - getDiscount(initialPrice, sequenceOfTheDay, specialCode, hourOfTheDay);
	}

	private double getDiscount(final double movieActualPrice, final int showSequence, final int specialCode,
			final int hourOfTheDay) {
		double specialDiscount = 0;
		double timingsDiscount = 0;
		if (MOVIE_CODE_SPECIAL == specialCode) {
			specialDiscount = movieActualPrice * 0.2; // 20% discount for special movie
		}

		if (hourOfTheDay >= 11 && hourOfTheDay < 16) {
			timingsDiscount = movieActualPrice * 0.25; // 25% discount for Timings
		}

		double sequenceDiscount = 0;
		if (showSequence == 1) {
			sequenceDiscount = 3; // $3 discount for 1st show
		} else if (showSequence == 2) {
			sequenceDiscount = 2; // $2 discount for 2nd show
		} else if (showSequence == 7) {
			sequenceDiscount = 1; // $1 discount for 7th show
		}
		final var discount = specialDiscount > timingsDiscount ? specialDiscount : timingsDiscount;
		// biggest discount wins
		return sequenceDiscount > discount ? sequenceDiscount : discount;

	}

	public String formatDuration(final Duration duration) {
		final long hour = duration.toHours();
		final long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

		return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin,
				handlePlural(remainingMin));
	}

	private String handlePlural(final long value) {
		return value == 1 ? "" : "s";
	}

	public double totalAmount(final double ticketPrice, final int audienceCount) {
		return ticketPrice * audienceCount;
	}
}