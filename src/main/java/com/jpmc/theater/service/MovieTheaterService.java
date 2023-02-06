package com.jpmc.theater.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.theater.exception.MovieTheaterException;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.ReservationDto;
import com.jpmc.theater.model.ShowDetails;
import com.jpmc.theater.util.MovieTheaterUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MovieTheaterService {

	@Autowired
	private MovieTheaterUtil movieTheaterUtil;

	public List<ShowDetails> moviesSchedule() {
		final var moviesSchedule = movieTheaterUtil.moviesSchedule();
		return moviesSchedule.stream().map(movieSchedule -> {
			movieSchedule.getMovie().setTicketPrice(movieTheaterUtil.calculateTicketPrice(movieSchedule));
			movieSchedule.getMovie()
					.setDuration(movieTheaterUtil.formatDuration(movieSchedule.getMovie().getRunningTime()));
			return movieSchedule;
		}).collect(Collectors.toList());
	}

	public Reservation reserve(final ReservationDto reservationDto) throws MovieTheaterException {
		final var moviesSchedule = moviesSchedule();

		ShowDetails showDetails;
		try {
			showDetails = moviesSchedule.get(reservationDto.getSequence() - 1);
		} catch (final RuntimeException ex) {
			log.error("Exception Occured in reservation : " + ex);
			throw new MovieTheaterException(ex);
		}
		final var numberOfTickets = reservationDto.getNumberOfTickets();
		final var ticketPrice = showDetails.getMovie().getTicketPrice();
		final var totalAmount = movieTheaterUtil.totalAmount(ticketPrice, numberOfTickets);
		return new Reservation(reservationDto.getCustomer(), showDetails, numberOfTickets, totalAmount);
	}
}
