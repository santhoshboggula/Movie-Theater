package com.jpmc.theater.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.jpmc.theater.MovieTheaterApplication;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.ReservationDto;
import com.jpmc.theater.model.ShowDetails;

@SpringBootTest(classes = MovieTheaterApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class MovieTheaterControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testMoviesSchedule() {
		System.out.println(port);
		final List<ShowDetails> response = this.restTemplate
				.getForObject("http://localhost:" + port + "/api/movietheater/movies/schedule", List.class);
		assertNotNull(response);
		assertEquals(9, response.size());
	}

	@Test
	void testReserve() {

		final ReservationDto reservation = ReservationDto.builder().customer(Customer.builder().name("john").build())
				.sequence(3).numberOfTickets(10).build();
		final ResponseEntity<Reservation> responseEntity = this.restTemplate.postForEntity(
				"http://localhost:" + port + "/api/movietheater/movies/reserve", reservation, Reservation.class);
		System.out.println(responseEntity.getBody());
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("john", responseEntity.getBody().getCustomer().getName());
	}
}
