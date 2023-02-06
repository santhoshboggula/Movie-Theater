package com.jpmc.theater.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpmc.theater.exception.MovieTheaterException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.ReservationDto;
import com.jpmc.theater.model.ShowDetails;
import com.jpmc.theater.util.MovieTheaterUtil;

@SpringBootTest
class MovieTheaterServiceTest {

	@Mock
	private MovieTheaterUtil movieTheaterUtil;

	@InjectMocks
	private MovieTheaterService movieTheaterService;

	@Test
	void moviesScheduleTest() {
		final List<ShowDetails> moviesSchedule = getShowDetails();
		Mockito.when(movieTheaterUtil.moviesSchedule()).thenReturn(moviesSchedule);
		Mockito.when(movieTheaterUtil.calculateTicketPrice(any(ShowDetails.class))).thenReturn(8.0);
		final List<ShowDetails> response = movieTheaterService.moviesSchedule();
		assertThat(8.0, equalTo(response.get(0).getMovie().getTicketPrice()));
	}

	@Test
	void reserveTest() throws MovieTheaterException {
		final List<ShowDetails> moviesSchedule = getShowDetails();
		Mockito.when(movieTheaterUtil.moviesSchedule()).thenReturn(moviesSchedule);
		Mockito.when(movieTheaterUtil.calculateTicketPrice(any(ShowDetails.class))).thenReturn(8.0);
		Mockito.when(movieTheaterUtil.totalAmount(anyDouble(), anyInt())).thenReturn(40.0);

		final ReservationDto reservationDto = getReservationDTO();
		final Reservation response = movieTheaterService.reserve(reservationDto);
		assertThat(40.0, equalTo(response.getTotalAmount()));
	}

	private List<ShowDetails> getShowDetails() {
		final List<ShowDetails> moviesSchedule = new ArrayList<>();
		final Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
		final ShowDetails showDetails = new ShowDetails(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
		moviesSchedule.add(showDetails);
		return moviesSchedule;
	}

	private ReservationDto getReservationDTO() {
		final Customer customer = new Customer("abc", "1");
		final ReservationDto reservationDTO = new ReservationDto();
		reservationDTO.setSequence(1);
		reservationDTO.setCustomer(customer);
		reservationDTO.setNumberOfTickets(5);
		return reservationDTO;
	}
}
