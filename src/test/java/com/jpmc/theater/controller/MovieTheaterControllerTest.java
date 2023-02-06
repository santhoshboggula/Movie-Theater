package com.jpmc.theater.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpmc.theater.exception.MovieTheaterException;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.ReservationDto;
import com.jpmc.theater.model.ShowDetails;
import com.jpmc.theater.service.MovieTheaterService;

@ExtendWith(MockitoExtension.class)
class MovieTheaterControllerTest {

	@InjectMocks
	MovieTheaterController movieTheaterController;

	@Mock
	MovieTheaterService movieTheaterService;

	@Test
    void testMoviesSchedule() {
    	when(movieTheaterService.moviesSchedule()).thenReturn(List.of(new ShowDetails(new Movie(), 1,LocalDateTime.now())));
    	final var showDetails = movieTheaterController.moviesSchedule();
    	assertNotNull(showDetails);
    	assertEquals(1, showDetails.size());
    }

	void testReservation() throws MovieTheaterException {
		final ReservationDto reservationDto = ReservationDto.builder().build();
		when(movieTheaterService.reserve(reservationDto)).thenReturn(Reservation.builder().audienceCount(1).build());
		final var reservation = movieTheaterController.reserve(reservationDto);
		assertNotNull(movieTheaterService.reserve(reservationDto));
		assertEquals(1, reservation.getAudienceCount());
	}

}
