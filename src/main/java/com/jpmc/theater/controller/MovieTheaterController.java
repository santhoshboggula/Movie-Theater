package com.jpmc.theater.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.theater.exception.MovieTheaterException;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.ReservationDto;
import com.jpmc.theater.model.ShowDetails;
import com.jpmc.theater.service.MovieTheaterService;

@RestController
@RequestMapping("/api/movietheater/")
public class MovieTheaterController {

	@Autowired
	private MovieTheaterService movieTheaterService;

	@GetMapping("movies/schedule")
	public List<ShowDetails> moviesSchedule() {
		return movieTheaterService.moviesSchedule();
	}

	@PostMapping("movies/reserve")
	public Reservation reserve(@Valid @RequestBody final ReservationDto reservationDto) throws MovieTheaterException {
		return movieTheaterService.reserve(reservationDto);

	}

}
